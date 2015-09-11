# Problema: Empaquetado 2D sin cortes guillotina #
**Descripcíon**

Sea dado un conjunto de n items rectangulares y un número ilimitado de bins rectangulares idénticos de amplitud W y altura H. Sean wj y hj  la amplitud (width) y altura (height), respectivamente, del item j. Deseamos empaquetar, sin solapamiento, todos los items en el menor número de bins. Se asume que lo items tienen orientación fija, es decir, no pueden rotarse.
Supondremos, sin pérdida de generalidad, que wj, hj, W, H son enteros, y que wj ≤ W y hj ≤ H (j = 1, …, n).


**Otra descripción**

Dado un conjunto de n items bidimensionales (rectángulos), {i1,i2,...,in}, de dimensiones respectivas (h(i1),w(i1)),(h(i2),w(i2)),...,(h(in),w(in)), determinar el menor número de bins (rectángulos) de dimensiones (H,W) necesarios para empaquetar sin solapamientos todos los items. Se supondrá que h(ij) ≤ H, w(ij) ≤ W, ∀j = 1,...,n y que los items no pueden rotarse. El empaquetado resultante no necesariamente debe poder obtenerse con cortes tipo guillotina.