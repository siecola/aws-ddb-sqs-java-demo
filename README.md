Esse é um projeto de demonstração da minha apresentação feita no TDC 2021 Innovation, na [trilha de DevOps](https://thedevconf.com/tdc/2021/innovation/trilha-devops), na palestra **Infraestrutura como código - criando recursos na nuvem com AWS CDK**. Os slides da palestra podem ser baixados dentro da sessão da palestra na trilha.

**São dois projetos:**

[aws-ecs-fargate-java-cdk-demo](https://github.com/siecola/aws-ecs-fargate-java-cdk-demo): projeto é construído com com o AWS Cloud Development Kit - CDK, para a modelagem da infraestrutura. Para maiores informações sobre isso o AWS CDK, consulte esse [post](https://siecola.com.br/blogs/aws_ecs_cdk.html).

[aws-ddb-sqs-java-demo](https://github.com/siecola/aws-ddb-sqs-java-demo): projeto construído com Spring Boot e Docker, para expor uma API simples, que acessa uma tabela do DynamoDB e uma fila do SQS.

Para preparar seu ambiente de desenvolvimento para trabalhar com o AWS CDK, consulte esse [link](https://docs.aws.amazon.com/cdk/latest/guide/work-with-cdk-java.html).

Na pasta **docs** desse repositório há uma collection do Postman para poder acessar o serviço criado, depois que ele é instalado na AWS. Configure a variável de ambiente **host** com o endereço da sua aplicação hospedada na AWS, que é o endereço do DNS do Application Load Balancer, informado no fim do deployment pelo terminal do CDK.



## Comandos úteis:

 * `mvn package`: compila e roda os testes;
 * `cdk ls`: lista todos as stacks do app;
 * `cdk synth`: sintetiza o template CloudFormation;
 * `cdk deploy`: faz o deploy da stack na conta/região da AWS;
 * `cdk diff`: compara a stack que já está na AWS com o estado atual do projeto;
 * `cdk docs`: abre a documentação do CDK.
