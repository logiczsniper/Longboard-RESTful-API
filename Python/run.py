""" Run the app at http://localhost:5000 """

from longboard_registry import app


app.run(host='0.0.0.0', port=5000, debug=True)
