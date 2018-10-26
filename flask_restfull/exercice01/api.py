# coding utf-8

from flask import Flask
from flask import Response
from flask import request

import json
import os

path_dir = 'files'

app = Flask(__name__)

@app.route('/arquivos/json', methods=['GET'])
def list_json():
    files = os.listdir(path_dir)

    json_retorno = [];

    for file in files:
        aux = {
            'file_name' : file
        }
        json_retorno.append(aux)

    return Response(response=json.dumps(json_retorno), mimetype='application/json')

@app.route('/arquivos/xml', methods=['GET'])
def list_xml():
    files = os.listdir(path_dir)

    json_retorno = [];

    for file in files:
        aux = {
            'file_name' : file
        }
        json_retorno.append(aux)

    return Response(response=json.dumps(json_retorno), mimetype='application/json')

@app.route('/arquivos/<file_name>', methods=['GET'])
def file_contents(file_name):
    full_path = path_dir + '/' + file_name

    content = ''
    status = True

    if os.path.exists(full_path):
        content = open(full_path).read()
    else:
        status = False
        content = 'Arquivo não existe!'

    if not status:
        return Response(response=json.dumps({'status': status, 'message': 'O arquivo não existe!'}), mimetype='application/json')
    else:
        return Response(response=json.dumps({'status': status, 'content': content}), mimetype='application/json')

@app.route('/arquivos/<file_name>', methods=['DELETE'])
def delete(file_name):
    full_path = path_dir + '/' + file_name

    status = True
    result = ''
    if not os.path.exists(full_path):
        status = False
        result = 'O aquivo não existe!'
    else:
        os.remove(full_path)
        result = 'Arquivo removido com sucesso!'

    return Response(response=json.dumps({'status': status, 'content': result}), mimetype='application/json')

if __name__ == '__main__':
    app.run(debug=True)
