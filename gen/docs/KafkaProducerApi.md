# KafkaProducerApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sendIConnectorConfigurationToIConnector**](KafkaProducerApi.md#sendIConnectorConfigurationToIConnector) | **POST** /iconnector-config.%s-%s | 105: Send iConnector configuration to iConnector


<a name="sendIConnectorConfigurationToIConnector"></a>
# **sendIConnectorConfigurationToIConnector**
> sendIConnectorConfigurationToIConnector(topic, body)

105: Send iConnector configuration to iConnector

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.KafkaProducerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    KafkaProducerApi apiInstance = new KafkaProducerApi(defaultClient);
    String topic = "topic_example"; // String | topic
    String body = "body_example"; // String | 
    try {
      apiInstance.sendIConnectorConfigurationToIConnector(topic, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling KafkaProducerApi#sendIConnectorConfigurationToIConnector");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **topic** | **String**| topic |
 **body** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**0** | Unexpected error |  -  |

