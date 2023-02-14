#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9
// Cria instância com MFRC522
MFRC522 mfrc522(SS_PIN, RST_PIN);   
 
char st[20];
bool state = 0;
bool stateRED = 1;

void setup() 
{
  Serial.begin(9600);   // Inicia comunicação Serial em 9600 
  SPI.begin();          // Inicia comunicação SPI
  mfrc522.PCD_Init();   // Inicia MFRC522
  pinMode(2, OUTPUT);   // Inicia o led verde no pino 2
  pinMode(3, OUTPUT);   // Inicia o led amarelo no pino 3
  pinMode(4, OUTPUT);   // Inicia o led vermelho no pino 4
}

void loop() 
{
  digitalWrite(4, stateRED);   
  // Verifica novos cartões
  if ( ! mfrc522.PICC_IsNewCardPresent()) 
  {
    return;
  }
  // Seleciona um dos cartões
  if ( ! mfrc522.PICC_ReadCardSerial()) 
  {
    return;
  }
  
  // Pega o UID da tag lida
  String conteudo= "";
  byte letra;
  for (byte i = 0; i < mfrc522.uid.size; i++) 
  {
    // Tranforma o UID em uma string
     conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " "));
     conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  
  conteudo.toUpperCase();
  // Escreve na serial o conteúdo da string do ID
  Serial.println(conteudo.substring(1));

  // Se for o cartão do professor, o LED amarelo irá acender
  if(conteudo.substring(1) == "B4 D9 07 85"){
    state = !state;
    stateRED = !stateRED;
    digitalWrite(3, state); 
    digitalWrite(4, stateRED); 
    delay(200);
  } else {
    digitalWrite(2, HIGH); 
    delay(200);
    digitalWrite(2, LOW);
  }

  delay(1300);
}
