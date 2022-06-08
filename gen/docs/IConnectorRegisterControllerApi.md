# IConnectorRegisterControllerApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**register**](IConnectorRegisterControllerApi.md#register) | **POST** /api/v1/configuration/manager/register | 104: Send iConnector configuration to MQ


<a name="register"></a>
# **register**
> register(tenantId, connectorId, body)

104: Send iConnector configuration to MQ

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.IConnectorRegisterControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    IConnectorRegisterControllerApi apiInstance = new IConnectorRegisterControllerApi(defaultClient);
    String tenantId = "tenantId_example"; // String | TenantId.
    String connectorId = "connectorId_example"; // String | IConnector ID.
    String body = "body_example"; // String | 
    try {
      apiInstance.register(tenantId, connectorId, body);
    } catch (ApiException e) {
      System.err.println("Exception when calling IConnectorRegisterControllerApi#register");
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
 **tenantId** | **String**| TenantId. |
 **connectorId** | **String**| IConnector ID. |
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

