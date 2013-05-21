int pinIn = 7;
int in = 0;

void setup(){
 Serial.begin(9600);
 
 pinMode(pinIn,INPUT); 
}

void loop(){
  in = digitalRead(pinIn);
  if(in){
    Serial.println("A");
  }
  else{
    Serial.println("B");
  }
  delay(50);
}



