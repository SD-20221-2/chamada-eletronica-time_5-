import zmq, time
import serial
# Inicia a conexão do arduino, pela porta COM8, rate 9600
arduino = serial.Serial('COM8',9600)

context = zmq.Context()

# Cria o socket publisher
s = context.socket(zmq.PUB)
# Define o caminho pela porta 5555
p = "tcp://*:5555"
# Faz o bind do socket ao endereço
s.bind(p)
flag = False
tagProfessor = 'B4 D9 07 85'
status = 'inativo'
while True:

    # Recebe na variável tagID tudo que é escrito na serial do arduino
    tagID = str(arduino.readline().decode())
    tagID = tagID.replace("\n", "")
    tagID = tagID.strip()
    if(tagID == tagProfessor):
        flag = not(flag)
        if flag: status = 'ativo'
        else: status = 'inativo'
        print('Tag do professor reconhecida, sistema', status)
    
    # Faz o publish para todos os subscritos em TAG INFO com as informações da tag que acabou de receber pelo arduino (loop)
    if flag and tagID != tagProfessor :
        s.send_string("TAG INFO " + tagID)
        print('dados publicados')