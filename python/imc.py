nombreCompleto=input("ingrese su nombre")
pesoDigitado=input("ingrese su peso en kilogramos")
estaturaDigitada=input("ingrese su estatura en metros")
peso=float(pesoDigitado)
estatura=float(estaturaDigitada)
indiceMasaCorporal=peso/estatura**2
categoriadepeso=None
if indiceMasaCorporal >=30
    categoriadepeso = "obesidad"
else:
    if indiceMasaCorporal >=25
    categoriadepeso = "Sobrepreso"
        else:
            if indiceMasaCorporal >= 18.5
                categoriadepeso= "normal"
                    else:
                        categoriadepeso = "peso bajo"
print("el indice de masa corporal (IMC)  de", nombreCompleto, "es", indiceMasaCorporal,"y esta en", categoriadepeso)