# Docker imagem pronta no dockerHub

## Criando uma imagen e push para dockerhub a partir do projeto.

1. **Deletar imagens antigas**
    ```bash
        docker rmi rabbitmq-topic:0.1.10
    ``` 
2. **Build do projeto**
    ```bash
   docker build -t rabbitmq-topic:0.1.10 -f docker/Dockerfile .   
   ``` 
3. **Tag do build**
   ```bash
        docker tag 107f080e5f34 lucasgalo/rabbitmq-topic:0.1.10
   ```
4. **Login dockerhub**
   ```bash
        docker login
   ```
5. **Push da tag**
    ```bash
        docker push lucasgalo/rabbitmq-topic:0.1.10
    ```
