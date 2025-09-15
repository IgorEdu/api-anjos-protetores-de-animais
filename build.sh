#!/bin/bash

JAR_FILE=api-controle-adocoes-0.0.1-SHNAPSHOT.jar
IMAGE_NAME=api_controle_adocoes:latest

echo "### Iniciando o build do projeto..."
mvn clean package

if [ $? -ne 0 ]; then
    echo "Erro ao compilar o projeto. Abortando..."
    exit 1
fi

echo "### Build concluído com sucesso!"

echo "### Criando a imagem Docker..."
docker build -t $IMAGE_NAME .

if [ $? -ne 0 ]; then
    echo "Erro ao criar a imagem Docker. Abortando..."
    exit 1
fi

echo "### Imagem Docker '$IMAGE_NAME' criada com sucesso!"

echo "### Processo concluído!"
echo "O arquivo JAR foi gerado: target/$JAR_FILE"
echo "A imagem Docker foi criada com o nome: $IMAGE_NAME"

exit 0