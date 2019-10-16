/**
 * @file led.c
 * @author alpha
 * @date 01.08.2018
 *
 * @brief AVR ATMEGA328P 17-bit LED
 *
 * A program to represent 17-bit LEF with many types of patterns
 */

#include<avr/io.h>
#include<util/delay.h>
/**
 * li function is using for blink the Led
 */
void li(){
	PORTC=0b00011111;
	PORTB=0b00001111;
	_delay_ms(100);
	PORTC=0b00000000;
	PORTB=0b00000000;
	_delay_ms(100);
}
/**
 * reset Funtion is using for reset the led
 */
void reset(){
	for(uint8_t a=0;a<2;a++){

			PORTD=0b11111111;
			PORTC=0b00011111;
			PORTB=0b00001111;

			_delay_ms(500);

			PORTC=0b00000000;
			PORTB=0b00000000;
			PORTD=0b00000000;
			_delay_ms(500);
	}
}
int main(void){

	DDRD= 0XFF; /// PORTD has been set as output.
	DDRC=0b00011111; /// PORTC PC5 to PC0 has been set as output.
	DDRB=0b00001111; /// PORTB PB4 to PB0  has been set as output.

	PORTC=0b00000000; /// PORTC has been cleared.
	PORTB=0b00000000; /// PORTB has been cleared.

	uint8_t i,left,right,j,left2,right2; /// A few unsigned variables are declared.

	while(1){
		/**
		 * @pattern
		 * Patterns start from here
		 */
		for(j=0;j<2;j++){
		PORTD=0b00000000; /// PORTD has been cleared.
		left=0b10000000;
		left2=0b00010000;

		right2=0b00000001;
		right=0b11111111;
		for(i=0;i<8;i++){
			PORTD|=(left>>i); /// PORTD right shifted 8 times.
			li(); /// @li function called.
		}
		for(i=0;i<8;i++){
			PORTD &=(right>>i); /// PORTD right shifted 8 times.
			li(); /// @li function called.
		}
		}
		PORTD=0b00000000; /// PORTD has been cleared.
		_delay_ms(1000); /// Delay for 1s.

		reset(); /// @reset function called

		PORTD=0b11111111;

		for( j=0;j<2;j++){
			for(i=0;i<5;i++){
				PORTC=(left2>>i); /// PORTC right shifted 5 times.
				_delay_ms(200); ///Delay for 0.2s.
			}
			for(i=0;i<5;i++){
				PORTC=(right2<<i); ///PORTC left shifted 5 times.
				_delay_ms(200);
			}
			PORTC=0b00000000; /// PORTC cleared.
			left2=0b00010000;
			right2=0b00000001;
			for(i=0;i<3;i++){
				PORTC |= (left2>>i) | (right2<<i);

				PORTB^=(1<<PB1) | (1<<PB2);
				_delay_ms(200);
				PORTB^=(1<<PB0) | (1<<PB3);
				_delay_ms(200);

			}

			left2=0b00011100;
			right2=0b00000011;
			for(i=0;i<3;i++){
				PORTC=(left<<i) | (right>>i);
			}
			PORTC=0x00;
		}


		reset(); /// @reset function called.

		left2=0b0000001;


		for(i=0;i<4;i++){
			PORTB|=(left2<<i);
			_delay_ms(200);
		}

		left2=0b00010000;

		for(i=0;i<5;i++){
			PORTC|=(left2>>i);
			_delay_ms(200);
		}

		left=0b10000000;

		for(i=0;i<8;i++){
			PORTD|=(left>>i);
			_delay_ms(200);
		}

		reset(); ///@reset function called




	}
}
