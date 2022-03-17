# MobDev Rick and Morty Application
MobDev technical test

Esta es la prueba técnica de MobDev.

APIs source:
https://rickandmortyapi.com/documentation/#get-a-single-character
https://rickandmortyapi.com/documentation/#get-a-single-location

Exponer un endpoint que permita realizar una petición con el id de algún personaje de la serie, se debe
entregar una respuesta basándose en el siguiente JSON Schema:

{
"$schema": "http://json-schema.org/draft-07/schema",
"$id": "http://example.com/example.json",
"type": "object",
"required": [
"id",
"name",
"status",
"species",
"type",
"episode_count",
"origin"
],
"properties": {
"id": {
"$id": "#/properties/id",
"type": "int"
},
"name": {
"$id": "#/properties/name",
"type": "string"
},
"status": {
"$id": "#/properties/status",
"type": "string"
},
"species": {
"$id": "#/properties/species",
"type": "string"
},
"type": {
"$id": "#/properties/type",
"type": "string"
},
"episode_count": {
"$id": "#/properties/episode_count",
"type": "int"
},
"origin": {
"$id": "#/properties/origin",
"type": "object",
"required": [
"name",
"url",
"dimension",
"residents"
],
"properties": {
"name": {
"$id": "#/properties/origin/properties/name",
"type": "string"
},
"url": {
"$id": "#/properties/origin/properties/url",
"type": "string"
},
"dimension": {
"$id": "#/properties/origin/properties/dimension",
"type": "string"
},
"residents": {
"$id": "#/properties/origin/properties/residents",
"type": "array",
"items": {
"$id": "#/properties/origin/properties/residents/items",
"type": "string"
}
}
}
}
}
}
