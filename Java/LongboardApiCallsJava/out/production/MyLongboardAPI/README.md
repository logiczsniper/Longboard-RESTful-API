##### Introduction
The idea behind this project was to build an API that could complete the basic CRUD operations
on 'longboard' objects that are stored in a Shelve database. The following is the base 'longboard' object:
```json
{
    "id": "XXX",
    "name": "XXXXX",
    "length": "XX",
    "width": "XX"
}
```

##### Options
The following are the possible endpoints and their possible requests:

- To all longboards- <br>
        GET /longboards <br>
        POST /longboards <br>
        OPTIONS /longboards <br>
        DELETE /longboards

- To a specific longboard- <br>
        GET /longboard/{identifier} <br>
        HEAD /longboard/{identifier} <br>
        DELETE /longboard/{identifier} <br>
        PUT /longboard/{identifier} <br>
        PATCH /longboard/{identifier} <br>
        OPTIONS /longboard/{identifier}

##### Status Codes
I determined which status code to return based on the following information
provided [here](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes).
- `200` OK <br>
Standard response for successful HTTP requests.
- `201` Created <br>
The request has been fulfilled, resulting in the creation of a new resource.
- `204` No Content <br>
The server successfully processed the request and is not returning any content.
- `400` Bad Request <br>
The server cannot or will not process the request due to an apparent client error
- `404` Not Found <br>
The requested resource could not be found but may be available in the future.

##### Acknowledgments
There are two main sources of information that helped me incredibly: [Jake Wright](https://github.com/jakewright) and [Assertible](https://assertible.com/blog/7-http-methods-every-web-developer-should-know-and-how-to-test-them#head). Thanks for helping me learn more about APIs!

