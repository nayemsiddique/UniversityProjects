/*!
 *@file code.c
 *@author Alpha
 *@date 01.08.2018
 *
 *@brief Smart Home Controller using AVR ATMEGA328P USART
 *
 */

#include <avr/io.h>
#include <util/delay.h>
#include <stdio.h>
#define F_CPU 16000000UL
#define F_OSC 16000000
// PE0(atmega128) PD0(328P) or RXD0 -> Bluetooth HC-05 TX
//PE1(atmega128) PD1(328P) or TXD0-> Bluetooth HC-05 RX
///USART
#define BAUD 9600 /**< Baud Rate in bps. refer page 179 of 328p datasheet. */
#define MYUBRR F_OSC/16/BAUD-1 /**< UBRR = (F_CPU/(16*Baud))-1 for asynch USART page 179 328p datasheet. Baud rate 9600bps, assuming	16MHz clock UBRR0 becomes 0x0067*/
////////////////USART Start///////////////////////////////////////
void USART_init(unsigned int  ubrr) {

	UCSR0C = (0 << USBS0) | (3 << UCSZ00); /// Step 1. Set UCSR0C in Asynchronous mode, no parity, 1 stop bit, 8 data bits
	UCSR0A = 0b00000000; /// Step 2. Set UCSR0A in Normal speed, disable multi-proc

	UBRR0H = (unsigned char) (ubrr >> 8); /// Step 3. Load ubrr into UBRR0H and UBRR0L
	UBRR0L = (unsigned char) ubrr;

	UCSR0B = 0b00011000; /// Step 4. Enable Tx Rx and disable interrupt in UCSR0B
}
int USART_send(char c, FILE *stream) {

	while (!( UCSR0A & (1 << UDRE0))) /// Step 1.  Wait until UDRE0 flag is high. Busy Waitinig
	{
		;
	}

	UDR0 = c; /// Step 2. Write char to UDR0 for transmission
}
int USART_receive( FILE *stream) {

	while (!(UCSR0A & (1 << RXC0)))
		/// Step 1. Wait for Receive Complete Flag is high. Busy waiting
		;

	return (UDR0); /// Step 2. Get and return received data from buffer
}
//////////////USART END




int main() {
	USART_init(MYUBRR);
	// Initialise the standard IO handlers
		stdout = fdevopen(USART_send, NULL);
		stdin = fdevopen(NULL, USART_receive);
		DDRD=0XFF;//
		DDRC=0XFF;
		DDRB=0XFF;

	unsigned char command,fr_sta[3],micw_sta[3],gar_sta[3],ct1_sta[3],ct2_sta[3],ct3_sta[3],ct4_sta[3],flag='m',lvr_tv_sta[3],br_wh_sta[3];
	unsigned int fr_tem=-5,gra_spd,ct_heat[4],micw_pow,micw_min,micw_sec, kitchen=0,living_room=0,lvr_tv_vol,lvr_tv_prog,bathroom=0,br_wh_tem;


	struct light{ /// declare a structure named light
		unsigned char status[3];
		uint8_t red,green,blue;
	};
	struct light kl1,lvrl1,lvrl2,brl1,brl2,brl3;






	while(1){
	/// Start menu here
	if(flag=='m'){

		printf("*********************\n\rMy Home Control Panel\n\r*********************\n\r");
			printf("Type number on the left and Press enter to select\n\r1. Living Room\n\r2. Bathroom\n\r3.Kitchen\n\r4. Master Bed\n\r");
			scanf(" %c",&command);
			if(command=='3') flag='k'; /// make the flag=k for living room
			if(command=='1') flag='l'; // make the flag=l for bathroom
			if(command=='2') flag='b'; // make the flag = b for kitchen room


	}
	///End menu here

	/// Start Kitchen menu
	if(flag=='k'){
		while(1){
			if(flag=='m') break;
	if(flag=='k' && kitchen==0){

		printf("*********************\n\rMy Home Control Panel- Kitchen\n\r*********************\n\r");
			printf("Type number on the left and Press enter to select\n\r");
			printf("1. Fridge: Status: %d c %s\n\r",fr_tem,fr_sta);
			printf("2. Microwave Oven: Status: %s Power: %d Time: %d : %d MM:SS\n\r",micw_sta,micw_pow,micw_min,micw_sec);
			printf("3. Kitchen Light#1: Status: %s, Color: R: %d G: %d B: %d\n\r",kl1.status,kl1.red,kl1.green,kl1.blue);
			printf("4. Garbage Disposal Unit: Status: %s, Speed: %d\n\r",gar_sta,gra_spd);
			printf("5. Cook Top : One : Status: %s Heat : %d,Two : Status: %s Heat : %d,Three : Status: %s Heat : %d,Four : Status: %s Heat : %d\n\r",ct1_sta,ct_heat[0],ct2_sta,ct_heat[1],ct3_sta,ct_heat[2],ct4_sta,ct_heat[3]);
		    printf("6. Go Back\n\r");
		    scanf(" %c",&command);

		    if(command=='1'){

		    	   kitchen=1;
		    	}
		    else if(command=='2'){

		    	kitchen=2;
		        	}
		    else if(command=='3'){
		    	kitchen=3;
		    }

		    kitchen=4;


		   // else if(command=='5') kitchen=5;
		    //else printf("Please Enter Valid Command.. \n\r");

	}

    if(kitchen==1){

    	// start fridge menu
	while(1){

		printf("*********************\n\rMy Home Control Panel- Kitchen -Fridge\n\r*********************\n\r");
			printf("Type number on the left and Press enter to select\n\r");
			printf("1. ON \n\r2. OFF\n\r3. Temperature Control\n\r4. Go Back\n\r 5. Main Menu\n\r");

			scanf(" %c",&command);
			if(command=='1' && flag=='k'){
				fr_sta[0]='O';
				fr_sta[1]='N';
				fr_sta[2]=' ';
				PORTC|=(1<<PC0);

				printf("Fridge turn On\n\r");
			}
			else if(command=='2' && flag=='k'){
				fr_sta[0]='O';
						fr_sta[1]='F';
						fr_sta[2]='F';
						PORTC&=~(1<<PC0);

				printf("Fridge turn OFF\n\r");
			}
			else if(command=='4'){ // back to kitchen
				kitchen=0;
				flag='k';
				break;
			}
			else if(command=='5'){ //back to main menu
				flag='m';
				kitchen=0;
				break;
			}
			else if(command=='3'){
				printf("*********************\n\rMy Home Control Panel- Kitchen -Fridge -Temperature Control\n\r*********************\n\r");
				printf("Enter  Temperature -20 -4c\n\rPress enter when done # Goes back to fridge Menu\n\r");
				scanf(" %d",&fr_tem);
				//printf(" %d",fr_tem);

				flag='k';
				kitchen=1;
			}



	}
    }
	//End Fridge menu

	// Start microwave Oven menu
    else if(kitchen==2){
			while(1){
				printf("*********************\n\rMy Home Control Panel- Kitchen -Microwave Oven\n\r*********************\n\r");
					printf("Type number on the left and Press enter to select\n\r");
					printf("1. ON \n\r2. OFF\n\r3. Power\n\r4. Time\n\r5. Go Back\n\r 6. Main Menu\n\r");
					scanf(" %c",&command);
					if(command=='1'){
						    micw_sta[0]='O';
						    micw_sta[1]='N';
						    micw_sta[2]=' ';
						    PORTD|=(1<<PD6);
							printf("Microwave Oven turn On\n\r");
							command='0';
						}
				    else if(command=='2'){
							micw_sta[0]='O';
							micw_sta[1]='F';
							micw_sta[2]='F';
							PORTD&=~(1<<PD6);
							printf("Microwave Oven turn OFF\n\r");
							command='0';

						}
					else if(command=='3'){
							printf("*********************\n\rMy Home Control Panel- Kitchen -Microwave Oven -Power\n\r*********************\n\r");
							printf("Enter  Power 0-100 watt\n\rPress enter when done # Goes back to Microwave Oven Menu\n\r");
							scanf("%d",&micw_pow);
							//kitchen=2;
							command='0';


						}
					else if(command=='4'){

						printf("*********************\n\rMy Home Control Panel- Kitchen -Microwave Oven -Power\n\r*********************\n\r");
						printf("Enter  Time 00:00 – 59:59 MM:SS\n\rPress enter when done # Goes back to Microwave Oven Menu\n\r");
						printf("Enter Minutes:\n\r");
						scanf("%d",&micw_min);
						printf("%d\n\r",micw_min);
						printf("Enter Seconds:\n\r");
						scanf("%d",&micw_sec);
						//kitchen=2;
						command='0';

					}
					else if(command=='5'){
						kitchen=0;
						flag='k';
						command='0';
						break;

					}
					else if (command=='6'){
						kitchen=0;
						flag='m';
						command=0;
						break;
					}
		}
		}
		//End microwave Oven menu

		// Start Kitchen Light 1 menu
		else if(kitchen==3){
			uint8_t exit=0;

			while(1){

				printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1\n\r*********************\n\r");
					printf("Type number on the left and Press enter to select\n\r");
					printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
					scanf(" %c",&command);
					if(command=='1'){
						kl1.status[0]='O';
						kl1.status[1]='N';
						kl1.status[2]=' ';
						PORTD|=(1<<PD5);
					}
					else if(command=='2'){
						kl1.status[0]='O';
						kl1.status[1]='F';
						kl1.status[2]='F';
						PORTD&=~(1<<PD5);
					}

					else if(command=='3'){ //RGB menu
						while(1){
						printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
						//printf("Type number on the left and Press enter to select\n\r");
						printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

						scanf(" %c",&command);
						if(command=='1'){
							printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
							printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
							scanf(" %hhu",&kl1.red);
						}
						else if(command=='2'){
							printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
							printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
							scanf(" %hhu",&kl1.green);
						}
						else if(command=='3'){
							printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
							printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
							scanf(" %hhu",&kl1.green);
						}
						else if(command=='4'){
							break;
						}
						else if(command=='5'){
							exit=1;
							exit;

						}


						}

				}
					else if(command=='4'){

					}
					else if(command=='5'){

					}
					else printf("Please Enter a valid Command....\n\r");
			}

		}//ENd kitchen light 2 Menu

		// Start Garbage Disposal Menu
		else if(kitchen==4){
			while(1){
				printf("*********************\n\rMy Home Control Panel- Kitchen- Garbage Disposal\n\r*********************\n\r");
				printf("Type number on the left and Press enter to select\n\r");
				printf("1. ON \n\r2. OFF\n\r3.Speed Control\n\r4. Go Back\n\r5. Main Menu\n\r");
				scanf(" %c",&command);

				if(command=='1'){
					gar_sta[0]='O';
					gar_sta[1]='N';
					gar_sta[2]=' ';
					PORTD|=(1<<PD7);
				}
				else if(command=='2'){
					gar_sta[0]='O';
					gar_sta[1]='F';
					gar_sta[2]='F';
					PORTD&=~(1<<PD7);
				}
				else if(command=='3'){
					printf("*********************\n\rMy Home Control Panel- Kitchen- Garbage Disposal-Speed Control\n\r*********************\n\r");
					printf("Enter  Speed 0-10\n\rPress enter when done # Goes back to Garbage Disposal Menu\n\r");
					scanf("%d",&gra_spd);

				}
				else if(command=='4'){
					kitchen=0;
					flag='k';
					command='0';
					break;
				}
				else if(command=='5'){
					kitchen=0;
					flag='m';
					command=0;
					break;
				}


			}
		}
		// End Garbage Disposal Menu

    // Start Cook Top Menu

		else if(kitchen==5){
			while(1){
				printf("*********************\n\rMy Home Control Panel- Kitchen- Cook Top\n\r*********************\n\r");
				printf("Type number on the left and Press enter to select\n\r");
				printf("1. One : %s\n\r2.Two : %s \n\r3.Three : %s\n\r4.Four : %s\n\r5.All\n\r6. Go Back \n\r7. Main Menu\n\r",ct1_sta,ct2_sta,ct3_sta,ct4_sta);

				scanf(" %c",&command);

				if(command=='1'){
					if(ct1_sta[1]=='F'){
						ct1_sta[0]='O';
						ct1_sta[1]='N';
						ct1_sta[2]=' ';
						PORTC|=(1<<PC1);
					}
					else{
						ct1_sta[0]='O';
						ct1_sta[1]='F';
						ct1_sta[2]='F';
						PORTC&=~(1<<PC1);
					}


				}
				else if(command=='2'){
					if(ct2_sta[1]=='F'){
							ct2_sta[0]='O';
							ct2_sta[1]='N';
							ct2_sta[2]=' ';
						PORTD|=(1<<PD2);
							}
					else{
							ct2_sta[0]='O';
							ct2_sta[1]='F';
							ct2_sta[2]='F';
							PORTD&=~(1<<PD2);
						}
				}
				else if(command=='3'){

					if(ct3_sta[1]=='F'){
						PORTD|=(1<<PD3);
							ct3_sta[0]='O';
							ct3_sta[1]='N';
							ct3_sta[2]=' ';
							}
							else{
								PORTD&=~(1<<PD3);
							ct3_sta[0]='O';
							ct3_sta[1]='F';
							ct3_sta[2]='F';
						}

				}
				else if(command=='4'){
					if(ct4_sta[1]=='F'){
						PORTD|=(1<<PD4);
						ct4_sta[0]='O';
						ct4_sta[1]='N';
						ct4_sta[2]=' ';
							}
						else{
							PORTD&=~(1<<PD4);
							ct4_sta[0]='O';
							ct4_sta[1]='F';
							ct4_sta[2]='F';
						}
				}
				else if(command=='5'){

					PORTC|=(1<<PC1);
					PORTD|=(1<<PD2);
					PORTD|=(1<<PD3);
					PORTD|=(1<<PD4);

					ct1_sta[0]='O';
					ct1_sta[1]='N';
					ct1_sta[2]=' ';

					ct2_sta[0]='O';
					ct2_sta[1]='N';
					ct2_sta[2]=' ';

					ct3_sta[0]='O';
					ct3_sta[1]='N';
					ct3_sta[2]=' ';

					ct4_sta[0]='O';
					ct4_sta[1]='N';
					ct4_sta[2]=' ';


				}
				else if(command=='6'){
					kitchen=0;
					flag='k';
					command='0';
					break;
				}
				else if(command=='7'){
					kitchen=0;
					flag='m';
					command='0';
					break;
				}
			}
		}
    	// End Cook top menu



    }

	}
	///End Kitchen room menu

	/// Start Living Room menu
	if(flag=='l'){
			while(1){
				if(flag=='l' && living_room==0){
				printf("*********************\n\rMy Home Control Panel- Living Room\n\r*********************\n\r");
				printf("Type number on the left and Press enter to select\n\r");
				printf("1. TV\n\r2. Living Room Light#1: Status: %s, Color: R: %d G: %d B: %d\n\r",lvrl1.status,lvrl1.red,lvrl1.green,lvrl1.blue);
				printf("3. Living Room Light#2: Status: %s, Color: R: %d G: %d B: %d\n\r4. Go Back\n\r5. Main Menu\n\r",lvrl2.status,lvrl2.red,lvrl2.green,lvrl2.blue);
				scanf(" %c",&command);
				if(command=='1'){
					living_room=1;
				}
				else if(command=='2'){
					living_room=2;
				}
				else if(command=='3'){
					living_room=3;
				}
				}
				//TV Start
				if(living_room==1){
					while(1){
							printf("*********************\n\rMy Home Control Panel- Living Room-TV\n\r*********************\n\r");
							printf("Type number on the left and Press enter to select\n\r");
							printf("1. ON\n\r2. OFF\n\r3. Volume Control\n\r4. Program Control\n\r");
							scanf(" %c",&command);
							if(command=='1'){
								lvr_tv_sta[0]='O';
								lvr_tv_sta[1]='N';
								lvr_tv_sta[2]=' ';
								PORTC|=(1<<PC2);
							}
							else if(command=='2'){
								lvr_tv_sta[0]='O';
								lvr_tv_sta[1]='F';
								lvr_tv_sta[2]='F';
								PORTC&=~(1<<PC2);
							}
							else if(command=='3'){
								printf("*********************\n\rMy Home Control Panel- Living Room -TV\n\r*********************\n\r");
								printf("Enter Volume 0-100\n\rPress enter when done # Goes back to TV Menu\n\r");
								scanf(" %d",&lvr_tv_vol);

							}
							else if(command=='3'){
									printf("*********************\n\rMy Home Control Panel- Living Room -TV\n\r*********************\n\r");
									printf("Enter Program 0-100\n\rPress enter when done # Goes back to TV Menu\n\r");
									scanf(" %d",&lvr_tv_prog);

									}
					}
				}
				else if(living_room==2){
					while(1){
						printf("*********************\n\rMy Home Control Panel- Living Room-Kitchen Light#1\n\r*********************\n\r");
							printf("Type number on the left and Press enter to select\n\r");
											printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
											scanf(" %c",&command);
											if(command=='1'){
												lvrl1.status[0]='O';
												lvrl1.status[1]='N';
												lvrl1.status[2]=' ';
												PORTC|=(1<<PC3);
											}
											else if(command=='2'){
												lvrl1.status[0]='O';
												lvrl1.status[1]='F';
												lvrl1.status[2]='F';
												PORTC&=~(1<<PC3);
											}

											else if(command=='3'){ //RGB menu
												while(1){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
												//printf("Type number on the left and Press enter to select\n\r");
												printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

												scanf(" %c",&command);
												if(command=='1'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
													printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl1.red);
												}
												else if(command=='2'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
													printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl1.green);
												}
												else if(command=='3'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
													printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl1.green);
												}
												else if(command=='4'){
													break;
												}



												}

											}
											else if(command=='4'){

											}
											else if(command=='5'){

											}
											else printf("Please Enter a valid Command....\n\r");
					}
				}

				else if(living_room==3){
					while(1){
						printf("*********************\n\rMy Home Control Panel- Living Room-Kitchen Light#1\n\r*********************\n\r");
							printf("Type number on the left and Press enter to select\n\r");
											printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
											scanf(" %c",&command);
											if(command=='1'){
												lvrl2.status[0]='O';
												lvrl2.status[1]='N';
												lvrl2.status[2]=' ';
												PORTC|=(1<<PC4);
											}
											else if(command=='2'){
												lvrl2.status[0]='O';
												lvrl2.status[1]='F';
												lvrl2.status[2]='F';
												PORTC&=~(1<<PC4);
											}

											else if(command=='3'){ //RGB menu
												while(1){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
												//printf("Type number on the left and Press enter to select\n\r");
												printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

												scanf(" %c",&command);
												if(command=='1'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
													printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl2.red);
												}
												else if(command=='2'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
													printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl2.green);
												}
												else if(command=='3'){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
													printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
													scanf(" %hhu",&lvrl2.green);
												}
												else if(command=='4'){
													break;
												}



												}

											}
											else if(command=='4'){

											}
											else if(command=='5'){

											}
											else printf("Please Enter a valid Command....\n\r");
					}
				}
			}
	}
	/// End Living Room

	/// Start bathroom

	if(flag=='b'){
		while(1){
			if(bathroom==0){
				printf("*********************\n\rMy Home Control Panel- Bathroom\n\r*********************\n\r");
				printf("Type number on the left and Press enter to select\n\r");
				printf("1. Water Heater : Status: %s Temperature : %d \n\r",br_wh_sta,br_wh_tem);
				printf("2. Living Room Light#1: Status: %s, Color: R: %d G: %d B: %d\n\r4. Go Back\n\r5.",brl1.status,brl1.red,brl1.green,brl1.blue);
				printf("3. Living Room Light#2: Status: %s, Color: R: %d G: %d B: %d\n\r4. Go Back\n\r5.",brl2.status,brl2.red,brl2.green,brl2.blue);

    printf("3. Living Room Light#3: Status: %s, Color: R: %d G: %d B: %d\n\r4. Go Back\n\r5. Main Menu\n\r",brl3.status,brl3.red,brl3.green,lvrl2.blue);
    			scanf(" %c",&command);
    			if(command=='1'){
    				bathroom=1;
    			}
    			if(command=='2'){
    				bathroom=2;
    			}
    			if(command=='3') bathroom=3;
    			if(command=='4') bathroom=4;
    			if(command=='5') bathroom=5;
    			if(command==6) bathroom=6;



			}
			// Water Heater menu start
			if(bathroom==1){
				while(1){
				printf("*********************\n\rMy Home Control Panel- Bathroom - Water Heater\n\r*********************\n\r");
				printf("Type number on the left and Press enter to select\n\r");
				printf("1. ON\n\r2. OFF\n\r3. Temperature\n\r4.  Go Back\n\r5. Main Menu\r\n");
				scanf(" %c",&command);
				if(command=='1'){
					br_wh_sta[0]='O';
					br_wh_sta[1]='N';
					br_wh_sta[2]=' ';
				}
				else if(command=='2'){
					br_wh_sta[0]='O';
					br_wh_sta[1]='F';
					br_wh_sta[2]='F';
				}
				else if(command=='3'){
					printf("*********************\n\rMy Home Control Panel- Bathroom - Water Heater - Temperature\n\r*********************\n\r");
					printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
					scanf("%d",&br_wh_tem);
				}
				else if(command=='4'){
					;
				}
				else if(command=='5'){
					;
				}
				}

			}
           /// Light # 1 menu
			else if(bathroom==2){
				while(1){
					printf("*********************\n\rMy Home Control Panel- Bathroom - Water Heater - Living Room  Light#1\n\r*********************\n\r");
						printf("Type number on the left and Press enter to select\n\r");
										printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
										scanf(" %c",&command);
										if(command=='1'){
											brl1.status[0]='O';
											brl1.status[1]='N';
											brl1.status[2]=' ';
											PORTC|=(1<<PC4);
										}
										else if(command=='2'){
											brl1.status[0]='O';
											brl1.status[1]='F';
											brl1.status[2]='F';
											PORTC&=~(1<<PC4);
										}

										else if(command=='3'){ //RGB menu
											while(1){
											printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
											//printf("Type number on the left and Press enter to select\n\r");
											printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

											scanf(" %c",&command);
											if(command=='1'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
												printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl1.red);
											}
											else if(command=='2'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
												printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl1.green);
											}
											else if(command=='3'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
												printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl1.green);
											}
											else if(command=='4'){
												break;
											}



											}

										}
										else if(command=='4'){

										}
										else if(command=='5'){

										}
										else printf("Please Enter a valid Command....\n\r");
				}
			}

			else if(bathroom==3){
				while(1){
					printf("*********************\n\rMy Home Control Panel- Bathroom - Water Heater - Living Room  Light#1\n\r*********************\n\r");
						printf("Type number on the left and Press enter to select\n\r");
										printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
										scanf(" %c",&command);
										if(command=='1'){
											brl2.status[0]='O';
											brl2.status[1]='N';
											brl2.status[2]=' ';
											PORTC|=(1<<PC4);
										}
										else if(command=='2'){
											brl2.status[0]='O';
											brl2.status[1]='F';
											brl2.status[2]='F';
											PORTC&=~(1<<PC4);
										}

										else if(command=='3'){ //RGB menu
											while(1){
											printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
											//printf("Type number on the left and Press enter to select\n\r");
											printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

											scanf(" %c",&command);
											if(command=='1'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
												printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl2.red);
											}
											else if(command=='2'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
												printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl2.green);
											}
											else if(command=='3'){
												printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
												printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
												scanf(" %hhu",&brl2.green);
											}
											else if(command=='4'){
												break;
											}



											}

										}
										else if(command=='4'){

										}
										else if(command=='5'){

										}
										else printf("Please Enter a valid Command....\n\r");
				}
			}


			else if(bathroom==4){
						while(1){
							printf("*********************\n\rMy Home Control Panel- Bathroom - Water Heater - Living Room  Light#1\n\r*********************\n\r");
								printf("Type number on the left and Press enter to select\n\r");
												printf("1. ON \n\r2. OFF\n\r3.Light Color\n\r4. Go Back\n\r5. Main Menu\n\r");
												scanf(" %c",&command);
												if(command=='1'){
													brl3.status[0]='O';
													brl3.status[1]='N';
													brl3.status[2]=' ';
													PORTC|=(1<<PC4);
												}
												else if(command=='2'){
													brl3.status[0]='O';
													brl3.status[1]='F';
													brl3.status[2]='F';
													PORTC&=~(1<<PC4);
												}

												else if(command=='3'){ //RGB menu
													while(1){
													printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color\n\r*********************\n\rType number on the left and Press enter to select\n\r");
													//printf("Type number on the left and Press enter to select\n\r");
													printf("1. Change Red : Current Value %d\n\r1. Change Green : Current Value %d\n\r1. Change Blue : Current Value %d\n\r4. Go Back\n\r5. Main Menu\n\r",kl1.red,kl1.green,kl1.blue);

													scanf(" %c",&command);
													if(command=='1'){
														printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Red\n\r*********************\n\r");
														printf("Enter Red Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
														scanf(" %hhu",&brl3.red);
													}
													else if(command=='2'){
														printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Green\n\r*********************\n\r");
														printf("Enter Green Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
														scanf(" %hhu",&brl3.green);
													}
													else if(command=='3'){
														printf("*********************\n\rMy Home Control Panel- Kitchen-Kitchen Light#1 - Light Color- Blue\n\r*********************\n\r");
														printf("Enter Blue Value 0-255\n\rPress enter when done # Goes back to RGB Menu\n\r");
														scanf(" %hhu",&brl3.green);
													}
													else if(command=='4'){
														break;
													}



													}

												}
												else if(command=='4'){

												}
												else if(command=='5'){

												}
												else printf("Please Enter a valid Command....\n\r");
						}
					}



		}

	}






	}


	}

