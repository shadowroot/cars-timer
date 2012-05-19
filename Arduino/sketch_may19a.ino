byte c;
byte sync = 0;
int pin = 7;
byte in;
int it = 10;

void setup(){
 Serial.begin(9600);
 pinMode(pin,INPUT); 
}

void loop(){
  in = digitalRead(pin);
  if(it == 0){
    Serial.print("CI");
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
        Serial.print(in);
      }
    }
  }
  
  delay(100);
  it--;
}



