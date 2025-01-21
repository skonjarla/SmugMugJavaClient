# Java client for SmugMug API #

This library provides basic access to the SmugMug API. Given smugmug API credentials, it can be used to retrieve information about albums and images.
### What is this repository for? ###

* Provides basic access to the SmugMug API v2
* Performs required Oauth1.0 authentication
* Can be used to retrieve information about albums and images

### How do I get set up? ###
* First, need to have a SmugMug account and API key and Access token and it's corresponding secret.
* See https://api.smugmug.com/api/v2/doc/index.html for API documentation

* Use maven to build the jar. Typically,
```commandline
$ mvn clean install
```
* Must have Java 8+
* Must have a SmugMug account and API key and Access token and it's corresponding secret

### How do I use it? ###

* Create an instance of the OAuth1HttpClient class

```java
        OAuth1Signature.Builder signatureBuilder = new OAuth1Signature.Builder()
                .consumerKey(SM_CONSUMER_KEY)
                .consumerSecret(SM_CONSUMER_SECRET)
                .accessToken(SM_ACCESS_TOKEN)
                .tokenSecret(SM_TOKEN_SECRET);

        OAuth1HttpClient oAuthClient = new OAuth1HttpClient.Builder()
                .signatureBuilder(signatureBuilder)
                .build();
```
* Using System environment variables
* Following code snippet will create OAuth1Signature.Builder from environment variables
```java
    private OAuth1Signature.Builder getSmugMugSysVariables() {
        Map<String, String> env = System.getenv();
        String smConsumerKey = env.get("SM_CONSUMER_KEY");
        String smConsumerSecret = env.get("SM_CONSUMER_SECRET");
        String smTokenSecret = env.get("SM_TOKEN_SECRET");
        String smAccessToken = env.get("SM_ACCESS_TOKEN");

        if (smConsumerKey == null || smConsumerSecret == null || smTokenSecret == null || smAccessToken == null) {
            throw new IllegalStateException("Please set environment variables SM_CONSUMER_KEY, SM_CONSUMER_SECRET, SM_TOKEN_SECRET, SM_ACCESS_TOKEN");
        }
        return new OAuth1Signature.Builder()
                .consumerKey(smConsumerKey)
                .consumerSecret(smConsumerSecret)
                .accessToken(smAccessToken)
                .tokenSecret(smTokenSecret);
    }
```
* Pass the OAuth1HttpClient to any of the SmugMug API methods.
* Get a list of all images for a given album key
```java
        List<SMImage> images = Albums.getAllImagesOfAlbum(oAuthClient, ALBUM_KEY);
```
* Using Images API
```java
        Images.getImageByKey(oAuthClient, IMAGE_KEY);
        Images.getMetaDataOfImageByKey(oAuthClient, IMAGE_KEY);
```
* Updating an image
* Following code snippet will update an image's caption.
* Image URI is **mandatory**
```java
        SMImage image = Images.getImageByKey(oAuthClient, IMAGE_KEY);
                image = SMImage.builder()
                    .uri(image.getUri())
                    .caption("Caption")
                    .build();
                image = Images.updateImage(oAuthClient, image);
                System.out.println(image.getCaption());
```
* Using Nodes API. Following code will print all root nodes
```java
        List<SMNode> rootNodes = Nodes.getRootNodes(oAuthClient);
            rootNodes.forEach(node -> {
                System.out.println(node.getName() + " | " + node.getNodeID() + " | " +  node.getType());
            });
```
* Get all Nodes for a given Node ID
```java
        List<SMNode> childNodes.getNodes(oAuthClient, NODE_ID).forEach(node -> {
            System.out.println(node.getName() + " | " + node.getNodeID() + " | " + Paths.get(node.getUris().getAlbum().getUri()).getFileName() + " | " +node.getType());
        });
```
* Get album key for a given node ID
```java
System.out.println(Nodes.getAlbumKeyByNodeId(oAuthClient, NODE_ID));
```
* Using Albums API
* Get album by key
```java
        SMAlbum album = Albums.getAlbumByKey(oAuthClient, ALBUM_KEY);
        System.out.println(album.getName());
```
* Add an album to a folder
```java
SMNode response = Albums.addAlbum(oAuthClient, "FOLDER_ID", SMAlbum.builder().name("Test Album").build());
```
* Following code snippet will update an album's description
* Album URI is **mandatory**
```java
        SMAlbum smAlbum = Albums.getAlbumByKey(oAuthClient, ALBUM_KEY);
        smAlbum = SMAlbum.builder()
                .uri(smAlbum.getUri())
                .description("Test Album Test")
                .build();
        smAlbum = Albums.updateAlbum(oAuthClient, smAlbum);
        System.out.println(smAlbum.getDescription());
```
* Following code snippet will upload an image to an album
```java
        SMUploadResponse response = Upload.uploadFileToAlbum(oAuthClient, IMAGE_FILE_PATH, ALBUM_KEY);
        System.out.println(response.getStat() + " | " + response.getImage().getImageUri());
```
* To include more upload data use the ImageToUpload class
```java
        ImageToUpload imageToUpload = ImageToUpload.builder(IMAGE_FILE_PATH, ALBUM_KEY)
                        .title("title")
                        .keywords("keyword1, keyword2") // Comma separated keywords
                        .caption("caption")                
                        .build();
        SMUploadResponse response = Upload.uploadFileToAlbum(oAuthClient, imageToUpload);
        System.out.println(response.getStat() + " | " + response.getImage().getImageUri());
```
* Considering SmugMug offers a comprehensive API, here is the general information about request to any SmugMug API end point.
* Essentially, create Oauth1HttpClient
* Pass the OAuth1HttpClient to any of the SmugMug API methods.
```java
        ObjectMapper mapper = new ObjectMapper();
        String imageSizesEndpoint = new SmugMugApiConfig().hostName + Images.getImageByKey(oAuthClient, "IMAGE_KEY").getUris().getImageSizes().getUri();
        String imageSizes = SmugMugHttpRequestBuilder.create(oAuthClient, HttpGet.METHOD_NAME, imageSizesEndpoint, String.class)
                .executeRaw(rawResponse -> {
                    String entity = EntityUtils.toString(rawResponse.getEntity(), StandardCharsets.UTF_8); // Make API Request
                    TypeReference<HashMap<String, Object>> typeRef
                            = new TypeReference<HashMap<String, Object>>() {
                    };

                    HashMap<String, Object> o;

                    try {
                        o = mapper.readValue(entity, typeRef);
                        // Extract Response key from the returned JSON response.
                        HashMap<String, Object> r = mapper.convertValue(o.get("Response"),
                                new TypeReference<HashMap<String, Object>>() {
                                });
                        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
                    } catch (JsonProcessingException | IllegalArgumentException e) {
                        throw new RuntimeException(e);
                    }
                });
        System.out.println(imageSizes);
```
### Contribution guidelines ###

* Writing tests
* Code review
* Feature requests
