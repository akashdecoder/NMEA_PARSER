#include <bits/stdc++.h>
using namespace std;
namespace GPS{
  void parse(string str){
    string sen;
    stringstream s1(str);
    stringstream s2(str);
    int k=0;
    vector<string>tokens;
    string d, del;
    getline(s1,d,',');
    if(d.compare("$GPGGA") == 0){
      cout<<"GGA Data"<<endl;
      while(getline(s2,del,',')){
        tokens.push_back(del);
      }
      for(int i=0;i<tokens.size();i++){
        cout<<tokens[i]<<"\n";
      }
    }
    else if(d.compare("$GPGLL") == 0){
      cout<<"GLL Data"<<endl;
      while(getline(s2,del,',')){

        tokens.push_back(del);
      }
      for(int i=0;i<tokens.size();i++){
        cout<<tokens[i]<<"\n";
      }
    }
    if(d.compare("$GPRMC") == 0){
      cout<<"RMC Data"<<endl;
      while(getline(s2,del,',')){

        tokens.push_back(del);
      }
      for(int i=0;i<tokens.size();i++){
        cout<<tokens[i]<<"\n";
      }
    }
    if(d.compare("$GPGSV") == 0){
      cout<<"GSV Data"<<endl;
      while(getline(s2,del,',')){

        tokens.push_back(del);
      }
      while(k<tokens.size()){
        if(k == 1)
          cout<<"Total GSV sentences: "<<tokens[k++]<<endl;
        else if(k==2)
          cout<<"Message Number: "<<tokens[k++]<<endl;
        else if(k==3)
          cout<<"Total Stellite in view: "<<tokens[k++]<<endl;
        else if(k==4){
          while(k<=19){
            cout<<"PRN number: "<<tokens[k++]<<endl;
            cout<<"Satellite elevation in degress: "<<tokens[k++]<<endl;
            cout<<"Satellite azimuth in degrees: "<<tokens[k++]<<endl;
            if(k==19){
              string sen1="";
              sen = tokens[k];
              for(int i=0;i<sen.size();i++){
                if(sen[i] == '*')
                  break;
                else
                  sen1 = sen1+sen[i];
              }
              cout<<"Satellite SNR: "<<sen1<<endl;
              k++;
            }
            else
              cout<<"Satellite SNR: "<<tokens[k++]<<endl;
          }
        }
        else
          cout<<tokens[k++]<<endl;
      }
    }
    if(d.compare("$GPGSA") == 0){
      cout<<"GSA Data"<<endl;
      while(getline(s2,del,',')){
        tokens.push_back(del);
      }
      while(k < tokens.size()){
        if(k == 1){
          if(tokens[k] == "A")
            cout<<tokens[k++]<<" Automatic Mode"<<endl;
          else
            cout<<tokens[k++]<<" Manual Mode"<<endl;
        }
        else if(k==3){
          int j;
          for(j=3;j<=14;j++){
            cout<<"PRN"<<j-2<<"= "<<tokens[j]<<endl;
            k=j+1;
          }
        }
        else if(k == 15){
          string sen,sen1="";
          cout<<"PDOP= "<<tokens[k++]<<endl;
          cout<<"HDOP= "<<tokens[k++]<<endl;
          if(k==17){
            sen = tokens[k];
            for(int i=0;i<sen.size();i++){
              if(sen[i] == '*')
                break;
              else
                sen1 = sen1+sen[i];
            }
            cout<<"VDOP= "<<sen1<<endl;
            k++;
          }
          else
            cout<<"VDOP= "<<tokens[k++]<<endl;
        }
        else
          cout<<tokens[k++]<<endl;
      }
    }
    if(d.compare("$GPVTG") == 0){
      cout<<"VTG Data"<<endl;
      while(getline(s2,del,',')){

        tokens.push_back(del);
      }
      cout<<tokens[0]<<endl;
      cout<<"Track for Degress True: "<<tokens[1]<<endl;
      cout<<"Character Indicator: "<<tokens[2]<<endl;
      cout<<"Track for Magentic: "<<tokens[3]<<endl;
      cout<<"Character for Magnetic: "<<tokens[4]<<endl;
      cout<<"SOG(in Knots): "<<tokens[5]<<endl;
      cout<<"Character Indicator for knots: "<<tokens[6]<<endl;
      cout<<"SOG(kmph): "<<tokens[7]<<endl;
      string sen,sen1="";
      sen = tokens[8];
      for(int i=0;i<sen.size();i++){
        if(sen[i]=='*')
          break;
        sen1 = sen1 + sen[i];
      }
      cout<<"Character Indicator for km: "<<sen1<<endl;
    }
    if(d.compare("$GPZDA") == 0){
      cout<<"ZDA Data"<<endl;
      while(getline(s2,del,',')){

        tokens.push_back(del);
      }
      cout<<tokens[0]<<endl;
      cout<<"UTC Time: "<<tokens[1]<<endl;
      cout<<"Day: "<<tokens[2]<<endl;
      cout<<"Month: "<<tokens[3]<<endl;
      cout<<"Year: "<<tokens[4]<<endl;
      cout<<"Local Zone Description(00 to +/- 13 hrs): "<<tokens[5]<<endl;
      string sen,sen1="";
      sen = tokens[6];
      for(int i=0;i<sen.size();i++){
        if(sen[i]=='*')
          break;
        sen1 = sen1 + sen[i];
      }
      cout<<"Local Zone minutes Description: "<<sen1<<endl;
    }
    cout<<endl;
  }

  int checksumcalc(string nmea){
    char ch;
    int checksum = 0;
    for(int i=0;i<nmea.size();++i){
      ch = nmea[i];
      switch(ch){
        case '$':
          break;
        case '*':
          i = nmea.size();
          continue;
        default:
          if(checksum == 0){
            checksum = ch;
          }
          else{
            checksum = checksum^ch;
          }
          break;
      }
    }
    return checksum;
  }
}
string HexConvert(int n){
  string hex = "";
  char ch;
  while(n!=0){
    int temp = 0;
    temp=n%16;
    if(temp<10){
      ch =temp + 48;
      hex = hex + ch;
    }
    else{
      ch = temp+ 55;
      hex = hex + ch;
    }
    n = n/16;
  }
  reverse(hex.begin(),hex.end());
  return hex;
}
