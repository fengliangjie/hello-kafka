# KafkaConsumerApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getDataFromMQ**](KafkaConsumerApi.md#getDataFromMQ) | **GET** /iconnector-data.* | 106: get data from MQ
[**getIConnectorInfoFromMQ**](KafkaConsumerApi.md#getIConnectorInfoFromMQ) | **GET** /iconnector-info.* | 108: get iConnector info from MQ


<a name="getDataFromMQ"></a>
# **getDataFromMQ**
> getDataFromMQ(body)

106: get data from MQ

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.KafkaConsumerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    KafkaConsumerApi apiInstance = new KafkaConsumerApi(defaultClient);
    String body = "body_example"; // String | 
    try {
      apiInstance.getDataFromMQ(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling KafkaConsumerApi#getDataFromMQ");
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
 **body** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getIConnectorInfoFromMQ"></a>
# **getIConnectorInfoFromMQ**
> getIConnectorInfoFromMQ(body)

108: get iConnector info from MQ

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.KafkaConsumerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    KafkaConsumerApi apiInstance = new KafkaConsumerApi(defaultClient);
    String body = "body_example"; // String | 
    try {
      apiInstance.getIConnectorInfoFromMQ(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling KafkaConsumerApi#getIConnectorInfoFromMQ");
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
 **body** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

