#include <bits/stdc++.h>
#include <unistd.h>
#include "nmea_parser.h"

using namespace std;
using namespace GPS;

int main(){
  string nmea;
  while(1){
    cout<<"Enter a NMEA GPS data:\n";
    cin>>nmea;
    cout<<endl;
    parse(nmea);
    cout<<"Checksum Decimal= "<<checksumcalc(nmea)<<endl;
    cout<<"Checksum of GPS Data= "<<HexConvert(checksumcalc(nmea))<<endl;
    cout<<endl;
  }
  return 0;
}
