## spring-boot-hibernate-unidirectional-many-to-one-relationship-mapping

1- Implement hibernate unidirectional many-to-one relational mapping <br/>
2- ER diagram :  <br/>
NOT : Many OrderItem related to One CustomerOrder <br/>

![alt text](https://github.com/tufangorel/spring-boot-hibernate-unidirectional-many-to-one-relationship-mapping/blob/main/customer_order_item_er_diagram.png) <br/>


3- Start Spring Boot application with a specific profile such as "-Dspring.profiles.active=dev" . <br/>
4- swagger-ui can be accessed from URL : http://localhost:8080/customer-info/swagger-ui/ <br/><br/>

![alt text](https://github.com/tufangorel/spring-boot-hibernate-unidirectional-many-to-one-relationship-mapping/blob/main/order_item_swagger_ui.png)
<br/>

### Tech Stack
Java 11 <br/>
H2 Database Engine <br/>
spring boot <br/>
spring data jpa <br/>
spring web <br/>
hibernate <br/>
logback <br/>
maven <br/>
junit <br/>
springfox-swagger-ui <br/>
datasource-proxy <br/>
<br/>


## API OPERATIONS
### Save customer sucessfully to database

Method : HTTP.POST <br/>
URL : http://localhost:8080/customer/save <br/>

Request : 
<pre>
curl --location --request POST 'localhost:8080/customer-info/customer/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "streetName": "software",
        "city": "ankara",
        "country": "TR"
    }
}'
</pre><br/>

Response : 

HTTP response code 200 <br/>
<pre>
{
    "id": 1,
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "id": 1,
        "streetName": "software",
        "city": "ankara",
        "country": "TR"
    }
}
</pre>

### Create a new CustomerOrder for Customer id = 1.

Method : HTTP.POST <br/>
URL : localhost:8080/customer-info/customerorder/save <br/>

Request : 
<pre>
curl --location --request POST 'localhost:8080/customer-info/customerorder/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customer": {
        "age": 0,
        "id": 1,
        "name": "string",
        "shippingAddress": {
            "city": "string",
            "country": "string",
            "id": 1,
            "streetName": "string"
        }
    },
    "id": 0,
    "orderDate": "2021-05-04T15:23:33.229Z"
}'
</pre><br/>

Response : 

HTTP response code 200 <br/>
<pre>
{
    "id": 1,
    "orderDate": "2021-05-04T15:23:33.229",
    "customer": {
        "id": 1,
        "name": "name1",
        "age": 1,
        "shippingAddress": {
            "id": 1,
            "streetName": "software",
            "city": "ankara",
            "country": "TR"
        }
    }
}
</pre><br/>

### Create a new OrderItem for CustomerOrder id = 1.

Method : HTTP.POST <br/>
URL : localhost:8080/customer-info/orderitem/save <br/>

Request : 
<pre>
curl --location --request POST 'localhost:8080/customer-info/orderitem/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "customerOrder": {
        "customer": {
            "age": 0,
            "id": 1,
            "name": "string",
            "shippingAddress": {
                "city": "string",
                "country": "string",
                "id": 1,
                "streetName": "string"
            }
        },
        "id": 1,
        "orderDate": "2021-05-04T15:37:54.309Z"
    },
    "quantity": 5
}'
</pre><br/>

Response : 

HTTP response code 200 <br/>
<pre>
{
    "id": 1,
    "quantity": 5,
    "customerOrder": {
        "id": 1,
        "orderDate": "2021-05-04T15:37:54.309",
        "customer": {
            "id": 1,
            "name": "string",
            "age": 0,
            "shippingAddress": {
                "id": 1,
                "streetName": "string",
                "city": "string",
                "country": "string"
            }
        }
    }
}
</pre><br/>

### Find Customer with OrderItem id = 1.

Method : HTTP.GET <br/>
URL : localhost:8080/customer-info/orderitem/find/customer/orderitem/1 <br/>

Request : Request sent via query parameter.

Response : 

HTTP response code 200 <br/>
<pre>
{
    "id": 1,
    "name": "name1",
    "age": 1,
    "shippingAddress": {
        "id": 1,
        "streetName": "software",
        "city": "ankara",
        "country": "TR"
    }
}
</pre><br/>
