# Docker imagem pronta no dockerHub

## Criando uma imagen e push para dockerhub a partir do projeto.

1. **Deletar imagens antigas**
    ```bash
        docker rmi rabbitmq-topic:0.1.9
    ``` 
2. **Build do projeto**
    ```bash
   docker build -t rabbitmq-topic:0.1.9 -f docker/Dockerfile .   
   ``` 
3. **Tag do build**
   ```bash
        docker tag c6844c8dc8f9 lucasgalo/rabbitmq-topic:0.1.9
   ```
4. **Login dockerhub**
   ```bash
        docker login
   ```
5. **Push da tag**
    ```bash
        docker push lucasgalo/rabbitmq-topic:0.1.9
    ```
