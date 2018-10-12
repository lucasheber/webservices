# encodig utf-8

import json as JSON
import urllib2
import os

API = 'https://api.github.com/users/{}/followers'

user = raw_input("Forneca o usuario: ")

def get_followers( user ):
    followers = []

    try:
        name_file = API.format(user) + "/followers.txt"
        arquivo = ""

        if( os.path.isfile(name_file) ):
            # print name_file
            arquivo = open(name_file, "r")
            contents = arquivo.read()
        else:
            contents = urllib2.urlopen(name_file).read()
            os.makedirs(os.path.dirname(name_file))
            arquivo = open(name_file, "w")
            arquivo.write(contents)
            arquivo.close();

        
        json_followers = JSON.loads(contents);


        for follower in range(0, len(json_followers)):
            aux = {}

            aux['user'] = json_followers[follower]['login'].encode('utf-8')
            aux['url'] = json_followers[follower]['url']
            aux['reps'] = json_followers[follower]['repos_url']

            followers.append(aux)

        return followers
    except Exception as e:
        print ('Nao foi possivel enviar a requisicao ao servidor! ' + e.message);
        return followers

def get_repositorios(followers):
    for follower in followers:

        print "\n", follower['user'], "\n"

        try:

            name_file = follower['reps'] + "/repos.txt"
            arquivo = ""

            if( os.path.isfile(name_file) ):
                # print name_file
                arquivo = open(name_file, "r")
                contents = arquivo.read()
            else:
                contents = urllib2.urlopen(follower['reps']).read()
                os.makedirs(os.path.dirname(name_file))
                arquivo = open(name_file, "w")
                arquivo.write(contents)
                arquivo.close()

            
            repositories = JSON.loads(contents);

            for repositor in repositories:
                print "\t", repositor['name']

        except Exception as e:
            print ('Nao foi possivel enviar a requisicao ao servidor! ' + e.message );
            continue

followers = get_followers(user)

# print followers
if len(followers) > 0:
    print "\nUsuario da consulta:", user
    print len( followers ), "seguidor(es)"

    get_repositorios(followers)