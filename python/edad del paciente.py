nombrecompleto = input("digite el nombre del paciente:")
edaddigitada = input("digite la edad del paciente:")
edad = int(edaddigitada)
categoriaedad=None
if edad>=18:
    categoriaedad="Mayor de edad"
else:
    categoriaedad="Menor de edad"
print("el paciente", nombrecompleto, "es" ,categoriaedad)