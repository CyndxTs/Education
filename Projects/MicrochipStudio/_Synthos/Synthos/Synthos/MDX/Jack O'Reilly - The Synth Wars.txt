; By: Argedia & CyndxTs
; Declaracion de Constantes de Programa
.EQU NumNotas	=	21		; Cantidad de notas utilizadas en el programa
.EQU Pos_S		=	0 		; Posicion en la que se encuentra declarado el 'Silencio'. En este caso es '0', pues se declaró en la posición inicial.
.EQU Posiciones =	0x0060	; Dirección de Memoria en donde se empieza a almacenar las posiciones iniciales de cada sección dentro del patrón de secciones
.EQU Duraciones	=	0x0280	; Dirección de Memoria en donde se empieza a almacenar las duraciones de cada sección del patrón de secciones

; Vectores de Programa
.CSEG
.ORG 0		RJMP	Synthos
.ORG 0x26	RJMP	ProcesarSemifusa
.ORG 0x30

; Almacenamiento de Notas Musicales en Flash
NotasMusicales:
.DW 0x0     ; OCR1(A|B) de S
.DW 0x017B  ; OCR1(A|B) de E6
.DW 0x01A9  ; OCR1(A|B) de D6
.DW 0x01DD  ; OCR1(A|B) de C6
.DW 0x0238  ; OCR1(A|B) de A5
.DW 0x027D  ; OCR1(A|B) de G5
.DW 0x02CB  ; OCR1(A|B) de F5
.DW 0x02F6  ; OCR1(A|B) de E5
.DW 0x0353  ; OCR1(A|B) de D5
.DW 0x03BB  ; OCR1(A|B) de C5
.DW 0x0430  ; OCR1(A|B) de A4B4
.DW 0x0470  ; OCR1(A|B) de A4
.DW 0x04FB  ; OCR1(A|B) de G4
.DW 0x0547  ; OCR1(A|B) de F4G4
.DW 0x0597  ; OCR1(A|B) de F4
.DW 0x05EC  ; OCR1(A|B) de E4
.DW 0x06A6  ; OCR1(A|B) de D4
.DW 0x0777  ; OCR1(A|B) de C4
.DW 0x0861  ; OCR1(A|B) de A3B3
.DW 0x08E0  ; OCR1(A|B) de A3
.DW 0x09F7  ; OCR1(A|B) de G3

; Almacenamiento de las Secciones Musicales en Flash
SeccionesMusicales:
; SEC 1
.DB 0b10011011, 0b00000110
.DB 0b10000011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 2
.DB 0b10011011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 3
.DB 0b10010011, 0b00000110
.DB 0b10000011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 4
.DB 0b10010011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 5
.DB 0b10100011, 0b00000110
.DB 0b10001011, 0b00000110
.DB 0b01111011, 0b00000110
; SEC 6
.DB 0b10100011, 0b00000110
.DB 0b01111011, 0b00000110
; SEC 7
.DB 0b10011011, 0b00000110
.DB 0b10001011, 0b00000110
.DB 0b01111011, 0b00000110
; SEC 8
.DB 0b10011011, 0b00000110
.DB 0b01111011, 0b00000110
; SEC 9
.DB 0b10011011, 0b00000110
.DB 0b10001011, 0b00000110
.DB 0b10000011, 0b00000110
; SEC 10
.DB 0b10011011, 0b00000110
.DB 0b10000011, 0b00000110
; SEC 11
.DB 0b00110011, 0b00000110
.DB 0b01000011, 0b00000110
.DB 0b01011011, 0b00000110
; SEC 12
.DB 0b00110011, 0b00000110
.DB 0b01011011, 0b00000110
; SEC 13
.DB 0b00110011, 0b00000110
.DB 0b01000011, 0b00000110
.DB 0b01001011, 0b00000110
; SEC 14
.DB 0b00110011, 0b00000110
.DB 0b01001011, 0b00000110
; SEC 15
.DB 0b00110011, 0b00000110
.DB 0b00111011, 0b00000110
.DB 0b01001011, 0b00000110
; SEC 16
.DB 0b00110011, 0b00000110
.DB 0b00111011, 0b00000110
.DB 0b01000001, 0b01000010
.DB 0b00000010, 0b00000011
; SEC 17
.DB 0b01011010, 0b00000101
.DB 0b01100010, 0b00000101
.DB 0b01110010, 0b00000101
.DB 0b10001011, 0b00000110
.DB 0b01100001, 0b00000100
; SEC 18
.DB 0b01100011, 0b00000110
.DB 0b01011011, 0b00000110
.DB 0b01010011, 0b00000110
.DB 0b01001010, 0b01001011
.DB 0b00000100, 0b00000101
.DB 0b01010010, 0b00000101
.DB 0b01010010, 0b00000101
.DB 0b01001011, 0b00000110
.DB 0b01011010, 0b00000101
.DB 0b01100011, 0b00000110
.DB 0b01110010, 0b01110011
.DB 0b00000100, 0b00000101
; SEC 19
.DB 0b10000011, 0b00000110
.DB 0b01111011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 20
.DB 0b10000011, 0b00000110
.DB 0b01110011, 0b00000110
.DB 0b10000011, 0b00000110
.DB 0b01100001, 0b00000010
.DB 0b01011011, 0b00000110
.DB 0b01100011, 0b00000110
.DB 0b01101001, 0b00000010
; SEC 21
.DB 0b01011011, 0b00000110
.DB 0b01100011, 0b00000110
.DB 0b10000011, 0b00000110
.DB 0b01100001, 0b00000010
.DB 0b01011011, 0b00000110
.DB 0b01100011, 0b00000110
.DB 0b10000010, 0b10000011
.DB 0b00000100, 0b00000101
; SEC 22
.DB 0b01110011, 0b00000110
.DB 0b01111011, 0b00000110
; SEC 23
.DB 0b01000011, 0b00000110
.DB 0b01011011, 0b00000110
.DB 0b01110011, 0b00000110
.DB 0b10011011, 0b00000110
; SEC 24
.DB 0b10000011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 25
.DB 0b01011011, 0b00000110
.DB 0b01110011, 0b00000110
; SEC 26
.DB 0b00110011, 0b00000110
.DB 0b01000011, 0b00000110
; SEC 27
.DB 0b10011011, 0b00000110
; SEC 28
.DB 0b10010011, 0b00000110
; SEC 29
.DB 0b01010011, 0b00000110
.DB 0b00110011, 0b00000110
.DB 0b10100011, 0b00000110
; SEC 30
.DB 0b10001011, 0b00000110
; SEC 31
.DB 0b01111011, 0b00000110
.DB 0b01001011, 0b00000110
; SEC 32
.DB 0b00111011, 0b00000110
; SEC 33
.DB 0b01111011, 0b00000110
.DB 0b01100011, 0b00000110
.DB 0b01001011, 0b00000110
; SEC 34
.DB 0b00100010, 0b00100011
.DB 0b00000100, 0b00000101
.DB 0b00111010, 0b00000101
.DB 0b00100010, 0b00000101
.DB 0b00111010, 0b00000101
.DB 0b00110011, 0b00000110
.DB 0b00111011, 0b00000110
.DB 0b01000001, 0b00000100
.DB 0b00100010, 0b00000101
.DB 0b00110010, 0b00000101
.DB 0b01000010, 0b00000101
.DB 0b00100010, 0b00100011
.DB 0b00000100, 0b00000101
.DB 0b00101010, 0b00000101
.DB 0b00110010, 0b00000101
.DB 0b00011001, 0b00011010
.DB 0b00000011, 0b00000100
.DB 0b00110010, 0b00110011
.DB 0b00000100, 0b00000101
.DB 0b00011010, 0b00011011
.DB 0b00000100, 0b00000101
.DB 0b00101010, 0b00101011
.DB 0b00000100, 0b00000101
; SEC 35
.DB 0b01000010, 0b00000101
.DB 0b01001010, 0b01001011
.DB 0b00000100, 0b00000101
.DB 0b01000010, 0b00000101
.DB 0b01000011, 0b00000110
.DB 0b01000010, 0b00000101
.DB 0b01001010, 0b00000101
.DB 0b01001001, 0b00000100
.DB 0b00111010, 0b00000101
.DB 0b01001010, 0b00000101
.DB 0b01000001, 0b01000010
.DB 0b00000011, 0b00000100

; Welcome to Synthos!
Synthos:
	; Inicializacion de Elementos [STACK]
	LDI		R16, high(RAMend)
	OUT		SPH, R16
	LDI		R16, low(RAMend)
	OUT		SPL, R16
	; Inicializacion de Elementos [I/O] {A (Entrada)}
	CLR		R16
	OUT		ddrA, R16
	; Inicializacion de Elementos [I/O] {B (Salida), C (Salida), D (Salida)}
	SER		R16
	OUT		ddrB, R16
	OUT		ddrC, R16
	OUT		ddrD, R16
	; Inicialización de Elementos [Visualizador de Audio]
	RCALL	CargarVisualizador
	; Inicializacion de Elementos [Patrón de Secciones]
	RCALL	CargarPatronDeSecciones
	; Activación de Programa
	EsperarActivacion:
	IN		R16,pinA
	CPI		R16,0
   	BREQ	EsperarActivacion
	; Inicialización de Elementos [Punteros a Datos de Sección]
	RCALL	InicializarPunterosDeSeccion
	; Inicializacion de Elementos [Timer Counter 0]
	; TCCR0 [CTC][N = 64]
	LDI		R16, (1 << WGM01 | 0 << WGM00 | 0<<CS02 | 1 << CS01 | 1 << CS00)
	OUT		TCCR0, R16
	; OCR0
	LDI		R16, 244
	OUT		OCR0, R16
	; TIMSK
	LDI		R16, (1 << OCIE0)
	OUT		TIMSK, R16
	; Inicializacion de Elementos [Timer Counter 1]
	; TCCR1A [CTC][N = 1]
	LDI		R16, (0 << WGM11 | 0 << WGM10 | 0 << COM1A1 | 1 << COM1A0)
	OUT		TCCR1A, R16
	; TCCR1B [CTC][N = 1]
	LDI		R16, (0 << WGM13 | 1 << WGM12 | 0 << CS12 | 0 << CS11 | 1 << CS10)
	OUT		TCCR1B, R16
	; OCR1A
	CLR		R16
	OUT		OCR1AH, R16
	OUT		OCR1AL, R16
	; Inicializacion de Elementos [Registros Auxiliares]
							; R16 = Registro Multipropósito
	CLR		R17				; R17 = Medida de Seccion
	LDI		R18, 1			; R18 = Duracion de Nota
							; R19 = Registro Multipropósito
							; R20 = Medida de Segmentación de Visualizador
							; R21 = Posición de Nota
							; R22 =	Valor Acumulado en Y [Matriz]
	CLR		R23				; R23 = Señal de Fin de Reproducción // Lo haría simplemente con 'BRIE' o 'BRID', pero por algun motivo no funciona .-.
	; Inicializacion de Elementos [Interrupciones]
	SEI
	; Dormammu he venido para negociar.jpg
	Dormammu:
	SBRS	R23, 0	
	RJMP	Dormammu
	EsperarDesactivacion:
	IN		R16, pinA
	CPI		R16, 0
	BRNE	EsperarDesactivacion
	RJMP	EsperarActivacion

; Inicialización de Punteros de Datos de Sección
InicializarPunterosDeSeccion:
	LDI		XH,	high(Posiciones)
	LDI		XL,	low(Posiciones)
	LDI		YH, high(Duraciones)
	LDI		YL, low(Duraciones)
RET

; Subrutina de Interrupcion por Semifusa
ProcesarSemifusa:
	; Validación por Duración de Nota Anterior
	DEC		R18
	BRNE	VisualizarNota
	; Validación por Duración de Sección Anterior
	CPI		R17, 0
	BRNE	CargarProximaNota
	; Carga de Datos de Próxima Sección
	CargarProximaSeccion:
	LD		R17, Y+
	CPI		R17, 0 
	BRNE	PosicionarSeccion
	RCALL	FinalizarReproduccion
	RJMP	FinDeSemifusa
	; Posicionamiento en Sección Cargada
	PosicionarSeccion:
	LDI		ZH, high(2*SeccionesMusicales)
	LDI		ZL, low(2*SeccionesMusicales)
	LD		R16, X+
	ADD		ZL, R16
	CLR		R16
	ADC		ZH, R16
	; Carga de Datos de Próxima Nota
	CargarProximaNota:
   	LPM		R16, Z+
	LDI		R18, 0b00100000
	LDI		R19, 0b00000111
	AND		R19, R16
	; Cálculo de Duración de Nota
	CalcularDuracion:
	DEC		R19
	BREQ	CalcularPosicion
	LSR		R18
	RJMP	CalcularDuracion
	; Cálculo de Posición de Nota
	CalcularPosicion:
	LSR		R16
	LSR		R16
	LSR		R16
	MOV		R19, R16
	LSL		R16
	CP		R21, R19
	BREQ	PosicionarNota
	MOV		R21, R19
	LDI		R22, 1
	; Posicionamiento en Nota Cargada
	PosicionarNota:
	PUSH	ZL
	PUSH	ZH
	LDI		ZH, high(2*NotasMusicales)
	LDI		ZL, low(2*NotasMusicales)
	ADD		ZL, R16
	CLR		R16
	ADC		ZH, R16
	; Reproducción de Nota
	ReproducirNota:
	LPM		R16, Z+
	LPM		R19, Z+
	OUT		OCR1AH, R19
	OUT		OCR1AL, R16
	DEC		R17
	POP		ZH
	POP		ZL
	; Visualización de Nota
	VisualizarNota:
	SBRS	R18, 0
	RCALL	ActualizarVisualizador
	; Fin de Procesamiento de Semifusa
	FinDeSemifusa:
RETI

; Subrutina de Visualización de Nota en Matriz LED
ActualizarVisualizador:
	; Validación de por Nueva Posición de Nota
	CPI		R22, 1
	BRNE	Actualizar_Y
	LDI		R16, 0b00000001
	CLR		R19
	; Validación por Nota de 'Silencio'
	CPI		R21, Pos_S
	BRNE	CalcularSegmento
	OUT		portC, R19
	JMP		FinDeVisualizacion
	; Cálculo de posición de Segmento [Posición X [Matriz]]
	CalcularSegmento:
	CP		R19, R21
	BRGE	Actualizar_X
	ADD		R19, R20
	LSL		R16
	JMP		CalcularSegmento
	; Actualización de Posición de Segmento [Posición X [Matriz]]
	Actualizar_X:
	COM		R16
	OUT		portB, R16
	; Actualización de Posición Y de Segmento
	Actualizar_Y:
	OUT		portC, R22
	LSL		R22
	INC		R22
	FinDeVisualizacion:
RET

; Subrutina de Finalización de Reproducción de Audio
FinalizarReproduccion:
	CLI
	LDI		R16, (0<<WGM11 | 0<<WGM10 | 0<<COM1A1 | 0<<COM1A0 | 0<<COM1B1 | 0<<COM1B0)
	OUT		TCCR1A, R16
	LDI		R16, (0<<WGM13 | 1<<WGM12 | 0<<CS12 | 0<<CS11 | 1<<CS10)
	OUT		TCCR1B, R16
	CLR		R16
	OUT		TCCR0, R16
	OUT		OCR0, R16
	OUT		TIMSK, R16
	OUT		portC, R16
	COM		R16
	OUT		portB, R16
	LDI		R23, 1
RET

; Subrutina de Carga de Visualizador de Audio
CargarVisualizador:
	; Inicialización de Elementos
	LDI		R16, 8
	CLR		R19
	CLR		R20
	; Cálculo de Medida de Segmentación de Notas en Visualizador
	CalcularSegmentacion:
	CPI		R19, NumNotas
	BRGE	FinDeSegmentacion
	ADD		R19, R16
	INC		R20
	JMP		CalcularSegmentacion
	FinDeSegmentacion:
RET
; Subrutina de Carga de Patrón de Secciones
CargarPatronDeSecciones:
	; Inicialización de Elementos [Punteros a Datos de Sección]
	RCALL	InicializarPunterosDeSeccion
	/* PARTE 'A' */
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 3 || 10 -> (16)
		LDI     R16, 10
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 3 || 10 -> (16)
		LDI     R16, 10
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 4 || 16 -> (20)
		LDI     R16, 16
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 5 || 20 -> (26)
		LDI     R16, 20
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 5 || 20 -> (26)
		LDI     R16, 20
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 6 || 26 -> (30)
		LDI     R16, 26
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 7 || 30 -> (36)
		LDI     R16, 30
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 7 || 30 -> (36)
		LDI     R16, 30
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 8 || 36 -> (40)
		LDI     R16, 36
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 9 || 40 -> (46)
		LDI     R16, 40
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 9 || 40 -> (46)
		LDI     R16, 40
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 10 || 46 -> (50)
		LDI     R16, 46
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
	/* PARTE 'A' */
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 1 || 0 -> (6)
		LDI     R16, 0
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 2 || 6 -> (10)
		LDI     R16, 6
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 3 || 10 -> (16)
		LDI     R16, 10
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 3 || 10 -> (16)
		LDI     R16, 10
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 4 || 16 -> (20)
		LDI     R16, 16
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 5 || 20 -> (26)
		LDI     R16, 20
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 5 || 20 -> (26)
		LDI     R16, 20
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 6 || 26 -> (30)
		LDI     R16, 26
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 7 || 30 -> (36)
		LDI     R16, 30
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 7 || 30 -> (36)
		LDI     R16, 30
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 8 || 36 -> (40)
		LDI     R16, 36
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 9 || 40 -> (46)
		LDI     R16, 40
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 9 || 40 -> (46)
		LDI     R16, 40
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 10 || 46 -> (50)
		LDI     R16, 46
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
	/* PARTE 'B' */
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 12 || 56 -> (60)
		LDI     R16, 56
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 12 || 56 -> (60)
		LDI     R16, 56
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 12 || 56 -> (60)
		LDI     R16, 56
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 11 || 50 -> (56)
		LDI     R16, 50
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 12 || 56 -> (60)
		LDI     R16, 56
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 13 || 60 -> (66)
		LDI     R16, 60
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 13 || 60 -> (66)
		LDI     R16, 60
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 14 || 66 -> (70)
		LDI     R16, 66
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 13 || 60 -> (66)
		LDI     R16, 60
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 13 || 60 -> (66)
		LDI     R16, 60
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 14 || 66 -> (70)
		LDI     R16, 66
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 15 || 70 -> (76)
		LDI     R16, 70
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 15 || 70 -> (76)
		LDI     R16, 70
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 16 || 76 -> (84)
		LDI     R16, 76
		ST      X+, R16
		LDI     R16, 8
		ST      Y+, R16
	/* PARTE 'C' */
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 18 || 94 -> (118)
		LDI     R16, 94
		ST      X+, R16
		LDI     R16, 24
		ST      Y+, R16
		; SEC 19 || 118 -> (124)
		LDI     R16, 118
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 20 || 124 -> (138)
		LDI     R16, 124
		ST      X+, R16
		LDI     R16, 14
		ST      Y+, R16
		; SEC 19 || 118 -> (124)
		LDI     R16, 118
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 18 || 94 -> (118)
		LDI     R16, 94
		ST      X+, R16
		LDI     R16, 24
		ST      Y+, R16
		; SEC 19 || 118 -> (124)
		LDI     R16, 118
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 21 || 138 -> (154)
		LDI     R16, 138
		ST      X+, R16
		LDI     R16, 16
		ST      Y+, R16
	/* PARTE 'D' */
		; SEC 22 || 154 -> (158)
		LDI     R16, 154
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 22 || 154 -> (158)
		LDI     R16, 154
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 23 || 158 -> (166)
		LDI     R16, 158
		ST      X+, R16
		LDI     R16, 8
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 23 || 158 -> (166)
		LDI     R16, 158
		ST      X+, R16
		LDI     R16, 8
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 || 174 -> (178)
		LDI     R16, 174
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 || 174 -> (178)
		LDI     R16, 174
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 27 || 178 -> (180)
		LDI     R16, 178
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 28 || 180 -> (182)
		LDI     R16, 180
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 29 || 182 -> (188)
		LDI     R16, 182
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 32 || 194 -> (196)
		LDI     R16, 194
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 33 || 196 -> (202)
		LDI     R16, 196
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 27 || 178 -> (180)
		LDI     R16, 178
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 [--] || 166 -> (168) [170 --]
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
	/* PARTE 'D' ++ ++ */
		; SEC 22 || 154 -> (158)
		LDI     R16, 154
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 22 || 154 -> (158)
		LDI     R16, 154
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 23 || 158 -> (166)
		LDI     R16, 158
		ST      X+, R16
		LDI     R16, 8
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 23 || 158 -> (166)
		LDI     R16, 158
		ST      X+, R16
		LDI     R16, 8
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 || 174 -> (178)
		LDI     R16, 174
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 || 174 -> (178)
		LDI     R16, 174
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 || 170 -> (174)
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 27 || 178 -> (180)
		LDI     R16, 178
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 28 || 180 -> (182)
		LDI     R16, 180
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 29 || 182 -> (188)
		LDI     R16, 182
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 32 || 194 -> (196)
		LDI     R16, 194
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 33 || 196 -> (202)
		LDI     R16, 196
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 27 || 178 -> (180)
		LDI     R16, 178
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 24 [--] || 166 -> (168) [170 --]
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 31 || 190 -> (194)
		LDI     R16, 190
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 33 || 196 -> (202)
		LDI     R16, 196
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
	/* PARTE 'E' */
		; SEC 34 || 202 -> (246)
		LDI     R16, 202
		ST      X+, R16
		LDI     R16, 44
		ST      Y+, R16
		; SEC 24 || 166 -> (170)
		LDI     R16, 166
		ST      X+, R16
		LDI     R16, 4
		ST      Y+, R16
		; SEC 25 [--] || 170 -> (172) [174 --]
		LDI     R16, 170
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 26 [++] || [174 ++] 176 -> (178)
		LDI     R16, 176
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 27 || 178 -> (180)
		LDI     R16, 178
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 33 || 196 -> (202)
		LDI     R16, 196
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 30 || 188 -> (190)
		LDI     R16, 188
		ST      X+, R16
		LDI     R16, 2
		ST      Y+, R16
		; SEC 33 || 196 -> (202)
		LDI     R16, 196
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
	/* PARTE 'F' */
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 18 || 94 -> (118)
		LDI     R16, 94
		ST      X+, R16
		LDI     R16, 24
		ST      Y+, R16
		; SEC 19 || 118 -> (124)
		LDI     R16, 118
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 20 || 124 -> (138)
		LDI     R16, 124
		ST      X+, R16
		LDI     R16, 14
		ST      Y+, R16
		; SEC 19 || 118 -> (124)
		LDI     R16, 118
		ST      X+, R16
		LDI     R16, 6
		ST      Y+, R16
		; SEC 17 || 84 -> (94)
		LDI     R16, 84
		ST      X+, R16
		LDI     R16, 10
		ST      Y+, R16
		; SEC 18 [-- -- --]|| 94 -> (112) [118 -- -- --]
		LDI     R16, 94
		ST      X+, R16
		LDI     R16, 18
		ST      Y+, R16
		; SEC 35 || 246 -> (270) -> (255 + 15)
		LDI     R16, 246
		ST      X+, R16
		LDI     R16, 24
		ST      Y+, R16
	; Marcador de Final
	LDI     R16, 0
	ST      X+, R16
	LDI     R16, 0
	ST      Y+, R16
RET
