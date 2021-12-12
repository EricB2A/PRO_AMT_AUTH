# PRO_AMT_AUTH
AMT AUTH est un service permettant l'authentification d'utilisateurs de l'application [SilkyRoad](https://github.com/EricB2A/PRO_AMT).

## Architecture logicielle 🏛
[schema](docs/pro_auth.drawio.png)
Le service d'authenfication est packagé dans un container docker qui est hébergé sur [Docker Hub](https://hub.docker.com/).

Un déploiement se fait en 2 étapes : 
- Dans un premier temps, [Docker-image.yml](.github/workflows/docker-image.yml) créé une nouvelle image docker contenant la dernière version du code.
Cette nouvelle image est ensuite déployée sur docker hub.
- Dans un second temps, [CD.yml](.github/workflows/CD.yml) se connecte à l'instance EC2 et lance un script qui a pour but de télécharger sur docker hub la dernière version de l'image, et lance le container associé


La spécification de l'API est disponible ici :
[Swagger hub](https://app.swaggerhub.com/apis/noahfusi/AMT_Silkyroad_Auth/V1.0-oas3)