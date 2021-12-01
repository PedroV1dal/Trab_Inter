# Trabalho Prático de PA/ED/POO
# Integrantes do Grupo:
# Daniel Luiz Campos
# John Weslley Silva Nascimento
# Matheus Santos Zanella
# Pedro Henrique Vidal
# Wilmington Trindade Bento Junior

from tkinter import *
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction import DictVectorizer
from sklearn.naive_bayes import MultinomialNB

df = pd.read_csv('nomes.csv')
Gênero = { "M": 1 , "F" : 0}
df.Gênero = [Gênero[item] for item in df.Gênero]


data = df.sample(frac=1)

names = data['Nome']
root = Tk()
root.title("Predição de Generos de Nomes através de Naive-Bayes")
#comando para não alterar a resolução da janela
root.resizable(False,False)
r = []

def calcular():
    #iniciando vetorizador de caracteres
    cv = CountVectorizer()
    #valor de X = Nomes
    X = cv.fit_transform(names.values.astype('U'))
    #valor de Y = Genero
    Y = data.Gênero
    #Treinando o algoritmo com 20% das amostras pra teste, com o tamanho de treinamento de 70%
    x_train, x_test, y_train, y_test = train_test_split(X,Y, train_size = 0.70 , test_size=0.20)
    #Criando o modelo multinomial para o treinamento
    model = MultinomialNB()
    model.fit(x_train,y_train)
    sample = []
    #Colhendo os valores inseridos nas textFields
    sample = [str(text_nome1.get()), str(text_nome2.get()), str(text_nome3.get())]
    vector = cv.transform(sample).toarray()
    r = model.predict(vector)
    #Condicionais para masculino e feminino
    if(r[0]==1):
        resultado1.set("Masculino")
    else:
        resultado1.set("Feminino")
    if(r[1]==1):
        resultado2.set("Masculino")
    else:
        resultado2.set("Feminino")
    if(r[2]==1):
        resultado3.set("Masculino")
    else:
        resultado3.set("Feminino")

resultado1 = StringVar()
resultado2 = StringVar()
resultado3 = StringVar()

frame_nomes = Frame(root)
#Criação de labels, textFields e botão de predição
label_teste = Label(root, text="Insira os nomes abaixo e clique no botao para prever:",font=("Arial", 20))
label_nome1 = Label(frame_nomes, text="Nome 1:",font=("Arial", 15))
label_nome2 = Label(frame_nomes, text="Nome 2:",font=("Arial", 15))
label_nome3 = Label(frame_nomes, text="Nome 3:",font=("Arial", 15))
text_nome1 = Entry(frame_nomes, font=('Arial 15'), width = 21)
text_nome2 = Entry(frame_nomes, font=('Arial 15'), width = 21)
text_nome3 = Entry(frame_nomes, font=('Arial 15'), width = 21)
label_resultado1=Label(frame_nomes,textvariable=resultado1,font=("Arial", 15))
label_resultado2=Label(frame_nomes,textvariable=resultado2,font=("Arial", 15))
label_resultado3=Label(frame_nomes,textvariable=resultado3,font=("Arial", 15))

cmd_predict = Button(frame_nomes, text="Fazer Prediçao dos nomes",font=("Arial", 15), command=calcular)


#posicionando todos os componentes no grid
label_teste.grid()
label_nome1.grid(row=0, column = 0)
label_nome2.grid(row=1, column = 0)
label_nome3.grid(row=2, column = 0)
text_nome1.grid(row=0, column = 1)
text_nome2.grid(row=1, column = 1)
text_nome3.grid(row=2, column = 1)
label_resultado1.grid(row=0, column = 2)
label_resultado2.grid(row=1, column = 2)
label_resultado3.grid(row=2, column = 2)
#criando o grid
frame_nomes.grid()
#criando o botão fora do frame principal
cmd_predict.grid(row=3,column = 1)
#exibindo a tela
root.mainloop()
