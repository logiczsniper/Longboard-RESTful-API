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

##### Project Structure
The project is divided into two sections: <br>
1. Java <br> On the Java side is the swift GUI and the HTTP methods using Unirest that are used by the GUI.
2. Python <br> On the Python side is the API itself. It is built with Flask and Flask-RESTful. In order to run the Java GUI, the Flask application is run locally.


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
<br><br>
Links to images:
- [Icon](https://thenounproject.com/term/longboard/377119/)
- [Background(unedited)](https://www.redbubble.com/people/fangpunk/works/15574296-longboard-wave-t-shirt?p=art-print)

Links to sounds:
- [Button click](http://soundbible.com/1705-Click2.html)


