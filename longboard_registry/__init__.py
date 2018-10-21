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
from bs4 import BeautifulSoup
from markdown import markdown

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


def read_md(section_number):
    """
    Reads from README.md, selects a designated section of the text to be returned by the OPTIONS requests below.

    :param section_number: which section to be selected- 0 or 1
    :type: int

    :return: the text of the selected section.
    :rtype: str
    """

    with open('README.md', 'r') as md_file:
        html = markdown(md_file.read())
        r_message = BeautifulSoup(html, 'html5lib').findAll('ul')[0].findAll('li')[section_number].findAll('p')[0].text

    return str(r_message)


class LongboardList(Resource):
    """ The API Resource that involves the list of Longboard objects. """

    @staticmethod
    def get():
        shelf = get_db()
        keys = list(shelf.keys())

        longboards = []

        for key in keys:
            longboards.append(shelf[key])

        r_status = 204 if len(longboards) == 0 else 200

        return {'message': 'Success', 'data': longboards}, r_status

    @staticmethod
    def post():
        parser = reqparse.RequestParser()

        for attribute in ['id', 'name', 'length', 'width']:
            parser.add_argument(attribute, required=True)

        args = parser.parse_args()

        shelf = get_db()
        shelf[args['id']] = args

        return {'message': 'Longboard registered', 'data': args}, 201

    @staticmethod
    def options():

        r_message = read_md(0)

        return {'message': r_message}, 200


class Longboard(Resource):
    """ The API Resource that involves individual Longboard objects. """

    @staticmethod
    def get(identifier):
        shelf = get_db()

        if identifier not in shelf:
            return {'message': 'Longboard not found', 'data': {}}, 404

        return {'message': 'Longboard found', 'data': shelf[identifier]}, 200

    @staticmethod
    def head(identifier):

        shelf = get_db()

        if identifier not in shelf:
            return '', 404

        return '', 204

    @staticmethod
    def delete(identifier):
        shelf = get_db()

        if identifier not in shelf:
            return {'message': 'Longboard not found', 'data': {}}, 404

        del shelf[identifier]
        return '', 200

    @staticmethod
    def put(identifier):

        shelf = get_db()

        if identifier not in shelf:
            return {'message': 'Longboard not found', 'data': {}}, 404

        parser = reqparse.RequestParser()

        for attribute in ['name', 'length', 'width']:
            parser.add_argument(attribute, required=True)

        args = parser.parse_args()

        shelf[identifier] = args

        return {'message': 'Longboard updated', 'data': shelf[identifier]}, 200

    @staticmethod
    def patch(identifier):

        shelf = get_db()

        if identifier not in shelf:
            return {'message': 'Longboard not found', 'data': {}}, 404

        parser = reqparse.RequestParser()

        for attribute in ['name', 'length', 'width']:
            parser.add_argument(attribute, required=False)

        args = parser.parse_args()

        if None not in args.values():
            return '', 400

        updated_dict = dict()

        for attribute in args.keys():
            if args.get(attribute) is not None:
                updated_dict[attribute] = args.get(attribute)

        shelf[identifier] = {**shelf[identifier], **updated_dict}

        return {'message': 'Longboard patched', 'data': shelf[identifier]}, 200

    @staticmethod
    def options(identifier):

        r_message = read_md(1).replace('{identifier}', identifier)

        return {'message': r_message}, 200


api.add_resource(LongboardList, '/longboards')
api.add_resource(Longboard, '/longboard/<string:identifier>')
