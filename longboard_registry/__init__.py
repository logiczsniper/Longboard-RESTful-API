""" A RESTful API that interacts with a Shelve database

Operations on longboards:
Create - add new data on a longboard
Read - get information on an existing longboard
Update - modify data on a longboard
Delete - remove data on a longboard
"""


import shelve
from flask import Flask, g
from flask_restful import Resource, Api, reqparse


app = Flask(__name__)
api = Api(app=app)


def get_db():
    """
    Opens the Shelve database.

    :return: the opened shelve database
    """

    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = shelve.open("longboards.db")
    return db


@app.teardown_appcontext
def teardown_db(exception):
    """
    After the app closes, the shelve database is closed.

    :param exception: if there is an exception, it will be printed.
    """

    db = getattr(g, '_database', None)
    if db is not None:
        db.close()
    if exception is not None:
        print(exception)


class LongboardList(Resource):
    """ The API Resource that involves the list of Longboard objects. """

    @staticmethod
    def get():
        shelf = get_db()
        keys = list(shelf.keys())

        longboards = []

        for key in keys:
            longboards.append(shelf[key])

        return {'message': 'Success', 'data': longboards}, 200

    @staticmethod
    def post():
        parser = reqparse.RequestParser()

        parser.add_argument('id', required=True)
        parser.add_argument('name', required=True)
        parser.add_argument('length', required=True)
        parser.add_argument('width', required=True)

        args = parser.parse_args()

        shelf = get_db()
        shelf[args['id']] = args

        return {'message': 'Longboard registered', 'data': args}, 200


class Longboard(Resource):
    """ The API Resource that involves individual Longboard objects. """

    @staticmethod
    def get(identifier):
        shelf = get_db()

        if not (identifier in shelf):
            return {'message': 'Longboard not found', 'data': {}}, 404

        return {'message': 'Longboard found', 'data': shelf[identifier]}, 200

    @staticmethod
    def delete(identifier):
        shelf = get_db()

        if not (identifier in shelf):
            return {'message': 'Longboard not found', 'data': {}}, 404

        del shelf[identifier]
        return '', 200

    @staticmethod
    def put(identifier):

        shelf = get_db()

        if not (identifier in shelf):
            return {'message': 'Longboard not found', 'data': {}}, 404

        parser = reqparse.RequestParser()

        parser.add_argument('name', required=False)
        parser.add_argument('length', required=False)
        parser.add_argument('width', required=False)

        args = parser.parse_args()

        # TODO: make the following more efficient- there is definitely shorter ways to do this.

        new_db = shelf[identifier]

        if args.get('name') is not None:
            new_db['name'] = args.get('name')

        if args.get('length') is not None:
            new_db['length'] = args.get('length')

        if args.get('width') is not None:
            new_db['width'] = args.get('width')

        shelf[identifier] = new_db

        return {'message': 'Longboard updated', 'data': shelf[identifier]}, 200


api.add_resource(LongboardList, '/longboards')
api.add_resource(Longboard, '/longboard/<string:identifier>')
