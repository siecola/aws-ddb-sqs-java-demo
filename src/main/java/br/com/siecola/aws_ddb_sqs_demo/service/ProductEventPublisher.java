package br.com.siecola.aws_ddb_sqs_demo.service;

import br.com.siecola.aws_ddb_sqs_demo.enums.EventType;
import br.com.siecola.aws_ddb_sqs_demo.model.Envelope;
import br.com.siecola.aws_ddb_sqs_demo.model.Product;
import br.com.siecola.aws_ddb_sqs_demo.model.ProductEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProductEventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(
            ProductEventPublisher.class);

    @Value("${aws.sqs.queue.product.events.url}")
    private String productEventsQueueUrl;

    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductEventPublisher(AmazonSQS amazonSQS, ObjectMapper objectMapper) {
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
    }

    public void publishProductEvent(Product product, EventType eventType) {
        ProductEvent productEvent = new ProductEvent(product, Instant.now().toEpochMilli());

        try {
            Envelope envelope = new Envelope(eventType, objectMapper.writeValueAsString(productEvent));

            SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
                    .withQueueUrl(productEventsQueueUrl)
                    .withMessageBody(objectMapper.writeValueAsString(envelope));

            SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageStandardQueue);

            LOG.info("Product event sent - Code: {} - MessageId: {}",
                    product.getCode(), sendMessageResult.getMessageId());
        } catch (JsonProcessingException e) {
            LOG.error("Failed to create product event message");
        }
    }
}
