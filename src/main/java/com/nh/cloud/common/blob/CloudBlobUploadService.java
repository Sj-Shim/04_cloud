package com.nh.cloud.common.blob;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlobProperties;
import org.springframework.beans.factory.annotation.Value;

@Service("cloudBlobUploadService")
public class CloudBlobUploadService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String connectionString;
    String localPath = "./data/";
    String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
    String fileNameDownloaded = "quickstart" + java.util.UUID.randomUUID() + "downloaded.txt";
    
	public void uploadProcess(String fileName) {
		// TODO Auto-generated method stub
		
	}

    public BlobServiceClient createConnection() {
        // Create a BlobServiceClient object using a connection string
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
      return blobServiceClient;
    }

    public BlobContainerClient createContainer() {
        BlobServiceClient blobServiceClient=createConnection();
        // Create a unique name for the container
        String containerName = "quickstartblobs" + java.util.UUID.randomUUID();
        // Create the container and return a container client object
        return blobServiceClient.createBlobContainer(containerName);
    }


    public void  uploadFileToStorageAccount() {
        BlobContainerClient blobContainerClient=createContainer();
        // Create a local file in the ./data/ directory for uploading and downloading

        BlobClient blobClient=blobContainerClient.getBlobClient(fileName);
        // Write text to the file
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(localPath + fileName, true);
            writer.write("Hello, World!");
            writer.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        blobClient.uploadFromFile(localPath+fileName);
    }


    public void listAllBlobsInContainer() {
        BlobContainerClient blobContainerClient=createContainer();
        PagedIterable<BlobItem> blobItem=blobContainerClient.listBlobs();
        blobItem.stream().forEach(blob -> System.out.println(blob.getName()));
    }


    public void downloadBlobs() {
        BlobContainerClient blobContainerClient=createContainer();
        BlobClient blobClient=blobContainerClient.getBlobClient(fileName);
        blobClient.downloadToFile(fileNameDownloaded);
    }


    public void deleteContainer() {
        BlobContainerClient blobContainerClient=createContainer();
        BlobClient blobClient=blobContainerClient.getBlobClient(fileName);
        blobClient.delete();
        // adding mete data
        Map<String,String> metadataMap=new HashMap<>();
        metadataMap.put("key","1");
        blobClient.setMetadata(metadataMap);
        // getting values from metadata
        BlobProperties blobProperties=blobClient.getProperties();
        Map<String,String> metadataMapretival= blobProperties.getMetadata();

    }


    
/*
 * The default credential first checks environment variables for configuration
 * If environment configuration is incomplete, it will try managed identity
 */
//DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();

// Azure SDK client builders accept the credential as a parameter
// TODO: Replace <storage-account-name> with your actual storage account name
//BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//        .endpoint("https://<storage-account-name>.blob.core.windows.net/")
//        .credential(defaultCredential)
//        .buildClient();
//     */
}
