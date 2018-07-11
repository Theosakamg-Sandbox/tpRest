#include <SPI.h>
#include <WiFi.h>
#include <PubSubClient.h>

char ssid[] = "IMIE-ETUDIANTS";
char password[] = "";

char server[] = "172.17.0.61";
char topic[] = "sensor/thermal";

WiFiClient wifiClient;
PubSubClient client(server, 1883, 0, wifiClient);

void setup() {
  Serial.begin(115200);

  Serial.print("Attempting to connect to Network named: ");
  Serial.println(ssid); 
  
  WiFi.begin(ssid);
  while ( WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(300);
  }

  Serial.println("\nYou're connected to the network");
  Serial.println("Waiting for an ip address");

  while (WiFi.localIP() == INADDR_NONE) {
    Serial.print(".");
    delay(300);
  }

  Serial.println("\nIP Address obtained");
  printWifiStatus();

  Serial.println("\nConnect to Brocker");
}

void loop() {
  if (!client.connected()) {
    Serial.println("Disconnected. Reconnecting....");

    if(!client.connect("CC3200", "root", "toor")) {
      Serial.println("Connection failed");
    } else {
      Serial.println("Connection success");
    }
  }

  if(client.publish(topic,"2018-07-11T12:27:58.659Z;A8;10.10;20.20")) {
    Serial.println("Publish success");
  } else {
    Serial.println("Publish failed");
  }

  delay(1000);
  
}

void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your WiFi IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}
