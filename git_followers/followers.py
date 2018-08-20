# encodig utf-8

import json as JSON
import urllib2


API = 'https://api.github.com/users/{}/followers'

user = raw_input("Forneca o usuario: ")

def get_followers( user ):
    
    contents = urllib2.urlopen(API.format(user)).read()

    json_followers = JSON.loads(contents);

    followers = []

    for follower in range(0, len(json_followers)):
        aux = {}

        aux['user'] = json_followers[follower]['login'].encode('utf-8')
        aux['url'] = json_followers[follower]['url']
        aux['reps'] = json_followers[follower]['repos_url']

        followers.append(aux)

    return followers


def get_repositorios(followers):
    for follower in followers:

        print "\n", follower['user'], "\n"

        contents = urllib2.urlopen(follower['reps']).read()

        repositories = JSON.loads(contents);

        for repositor in repositories:
            print "\t", repositor['name']


followers = get_followers(user)


print "\nUsuario da consulta:", user
print len( followers ), "seguidor(es)"

get_repositorios(followers)