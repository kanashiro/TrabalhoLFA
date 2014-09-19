TrabalhoLFA
===========

Trabalho de Linguagens Formais e Automatos (IME)


--------------------code-------------------------


fprograma(0,a,0).
fprograma(0,b,0).
vazio(0,1).


fprograma(1,a,2).

fprograma(2,a,3).

fprograma(3,a,3).
fprograma(3,b,3).


inicial(0).
final(3).


aceita(Palavra):-inicial(In),reconhece(Palavra,In).

reconhece([],EstCorr):-final(EstCorr),write(EstCorr).

reconhece([S|R],EstCorr):-fprograma(EstCorr,S,ProxEst),reconhece(R,ProxEst),write(EstCorr).

reconhece(P,EstCorr):-vazio(EstCorr, ProxEst),reconhece(P,ProxEst).

