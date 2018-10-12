# encoding utf-8

import requests

url = 'http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing='
param = '12345'

while True:

    r = requests.get(url + param)
    
    if r.text.find('nothing') == -1:
        if r.text != 'Yes. Divide by two and keep going.':
            print("Finalizou! A mensagem é: {}".format(r.text))
            break;

    arr = r.text.split()

    if r.text == 'Yes. Divide by two and keep going.':
        param = str(int(int(param) / 2))
    else:
        param = arr[len(arr) - 1]

    # print(param)
    # print(r.text)

    # cont -= 1