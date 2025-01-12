package net.konjarla.smugmug.client.api;

import net.konjarla.smugmug.client.OAuth1HttpClient;
import net.konjarla.smugmug.client.SmugMugHttpRequestBuilder;
import net.konjarla.smugmug.client.api.response.SMBaseResponse;
import net.konjarla.smugmug.client.api.response.SMNodeResponse;
import net.konjarla.smugmug.client.api.response.SMNodesResponse;
import net.konjarla.smugmug.model.SMNode;
import net.konjarla.smugmug.model.SMPages;
import net.konjarla.smugmug.model.SMUri;
import net.konjarla.smugmug.model.SMUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;

public class Nodes {

    private static final SmugMugApiConfig API_CONFIG = new SmugMugApiConfig("/api/v2/node/");
    private static final Logger log = LoggerFactory.getLogger(Nodes.class);

    private Nodes() {
    }

    /**
     * Retrieves the root node for the given authenticated user.
     *
     * @param client OAuth1HttpClient
     * @return SMNode object representing the root node
     */
    public static SMNode getRootNode(OAuth1HttpClient client) {
        SMUser authUser = Users.getAuthenticatedUser(client);
        SMUri nodeUri = authUser.getUris().getNode();
        String path = API_CONFIG.hostName + nodeUri.getUri();
        try {
            SMNodeResponse smNodeResponse = (SMNodeResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return smNodeResponse.getNode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the root nodes for the given authenticated user.
     *
     * @param client OAuth1HttpClient
     * @return List of SMNode objects
     */
    public static List<SMNode> getRootNodes(OAuth1HttpClient client) {
        SMNode rootNode = getRootNode(client);
        return getNodes(client, rootNode.getNodeID());
    }

    /**
     * Retrieves the child nodes of the given node.
     *
     * @param client OAuth1HttpClient
     * @param nodeId the ID of the node to retrieve the children of
     * @return List of SMNode objects
     */
    public static List<SMNode> getNodes(OAuth1HttpClient client, String nodeId) {
        String path = API_CONFIG.hostName + getNodeById(client, nodeId).getUris().getChildNodes().getUri();
        try {
            SMNodesResponse smNodesResponse = (SMNodesResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            SMPages pages = smNodesResponse.getPages();
            List<SMNode> result = smNodesResponse.getNodes();
            while (pages.getNextPage() != null) {
                path = API_CONFIG.hostName + pages.getNextPage();
                smNodesResponse = (SMNodesResponse) SmugMugHttpRequestBuilder
                        .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                        .execute();
                pages = smNodesResponse.getPages();
                result.addAll(smNodesResponse.getNodes());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the node with the given key.
     *
     * @param client OAuth1HttpClient
     * @param nodeId the key of the node to retrieve
     * @return SMNode object
     */
    public static SMNode getNodeById(OAuth1HttpClient client, String nodeId) {
        String path = API_CONFIG.hostName + API_CONFIG.path + nodeId;
        try {
            SMNodeResponse smNodeResponse = (SMNodeResponse) SmugMugHttpRequestBuilder
                    .create(client, API_CONFIG.requestVerb, path, SMBaseResponse.class)
                    .execute();
            return smNodeResponse.getNode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the album key for the given node ID. If the node is not an album, this method returns null.
     *
     * @param client OAuth1HttpClient
     * @param nodeId the ID of the node to retrieve the album key for
     * @return the album key, or null if the node is not an album
     */
    public static String getAlbumKeyByNodeId(OAuth1HttpClient client, String nodeId) {
        try {
            SMNode node = getNodeById(client, nodeId);
            if ("Album".equalsIgnoreCase(node.getType())) {
                return Paths.get(getNodeById(client, node.getNodeID()).getUris().getAlbum().getUri()).getFileName().toString();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
