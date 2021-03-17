package br.com.siecola.aws_ddb_sqs_demo.repository;

import br.com.siecola.aws_ddb_sqs_demo.model.Product;
import br.com.siecola.aws_ddb_sqs_demo.model.ProductKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ProductRepository extends CrudRepository<Product, ProductKey> {
    List<Product> findAllByPk(String username);

    Optional<Product> findByPkAndSk(String username, String code);
}