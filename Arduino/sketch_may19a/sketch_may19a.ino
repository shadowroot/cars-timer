byte c;
byte sync = 0;
int pin = 7;
byte in;
int it = 10;
byte crossed = 0;

void setup(){
 Serial.begin(9600);
 pinMode(pin,INPUT); 
}

void loop(){
  in = digitalRead(pin);
  if(in == HIGH){
    crossed = 0xff;
    while(Serial.available() <= 0){
       Serial.print('C'); 
    }
  }
  if(in == LOW && crossed == 0xff){
    while(Serial.available() <= 0){
      crossed = 0x00;
      Serial.print('E');
    }
  }
  if(it == 0){
    Serial.print("I");
    sync=0;
  }
  if(sync == 0){
    if(Serial.available() > 0){
      c = Serial.read();
      if(c == 'B'){
        sync = 1;
        it = 10;
      }
    }
    
  }
  else{
    if(Serial.available() <= 0){ /*Pokud neni zadny vstup*/
      if(sync == 1){
        Serial.print('A');
      }
    }
  }
  
  delay(100);
  it--;
}



