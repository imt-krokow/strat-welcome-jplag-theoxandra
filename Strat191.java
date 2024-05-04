package welcome.ia;
//import static java.lang.Integer.min;

//import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import welcome.Jeu;
import welcome.Joueur;
import welcome.Lotissement;
//import welcome.Joueur;
import welcome.Travaux;
import welcome.utils.Couleur;
//import welcome.utils.Couleur;
import welcome.utils.RandomSingleton;


public class Strat191 extends Strat{
    // bot Bidon


    //Trouve le minimum
   public static int findMinimum(int num1, int num2, int num3) {
        int min = num1;

        if (num2 < min) {
            min = num2;
        }

        if (num3 < min) {
            min = num3;
        }

        return min;
    }

    public static int findMaximum(int num1, int num2, int num3) {
        int max = num1;

        if (num2 > max) {
            max = num2;
        }

        if (num3 > max) {
            max = num3;
        }

        return max;
    }

     // Détermine la rareté d'un numéro en calculant sa distance de 7,5
     int raretéNuméro(int num){
        int res;
        if (num>8){
            res= num-8;
            return res;
        }
        else 
            res= 8-num;
            return res;
    }


    public Strat191(){
    }
    
    @Override
    public String nomVille(){
        return "BidonVille";
    }
    
    @Override
    public String nomJoueur(){
        return "RandMan";
    }

   
   

    //ArrayList 
    
    public ArrayList<Integer> construirePossibilite1(int numero, int joueur, Jeu j){
       
        int min=0; // Variable utiles
        ArrayList<Integer> possibilite= new ArrayList(); //List des possibilités Ã  construire
        for(int i=0; i<3; i++){//Pour chaque rue
            min=j.joueurs[joueur].ville.rues[i].taille-1; //on part de la fin
            
            while(min>=0  && (j.joueurs[joueur].ville.rues[i].maisons[min].numero==-1 || j.joueurs[joueur].ville.rues[i].maisons[min].numero > numero))
                min--; // on décrement le min tant qu'on a pas trouvé un numéro <=
            if(min<0 || j.joueurs[joueur].ville.rues[i].maisons[min].numero!=numero){

                min++;// On part de la case suivante
                while(min < j.joueurs[joueur].ville.rues[i].taille && j.joueurs[joueur].ville.rues[i].maisons[min].numero == -1){
                    possibilite.add((Integer)(min+ 100*i)); // on construit les possibilités tant qu'on a des cases vides
    
                    min++;
                }       
            }
           
        }
        return possibilite;
    } 



        /*private ArrayList<Integer> construireChoixPlacementBarriere(Joueur joueur) {
                //TODO CHECKER ET AJUSTER AVEC LES LOTISSEMENTS POUR LA CONSTRUCTION
                Lotissement l;
                int num=0;
                
                //Les possibilités seront ajoutés dans une liste
                //Une possibilité sera représenté par un entier ayant la valeur suivante: 100*n°rue + position dans la rue
                //Création de la liste
                ArrayList<Integer> possibilite= new ArrayList();
                possibilite.add(0);
                //On parcours les lotissements
                for(int i=0; i<joueur.ville.lotissements.size(); i++){
                    l=joueur.ville.lotissements.get(i);
                    if(l.dispo){//Si le lotissement est dispo (pas encore utilisé pour la validation d'un objectif)
                        if(joueur.verbose && l.rue.numero > num){
                            System.out.print("\nRue " + l.rue.numero + ":");
                            num++;
                        }
                        for(int j=l.debut+1; j<l.fin; j++){ //on ajoute aux possibilités les barriÃšres entre les 2 barriÃšres du lotissements   
                            if(joueur.ville.rues[l.rue.numero-1].maisons[j-1].numero <0 || joueur.ville.rues[l.rue.numero-1].maisons[j-1].numero != joueur.ville.rues[l.rue.numero-1].maisons[j].numero){
                                possibilite.add((Integer)((l.rue.numero-1) * 100 + j));
                                if(joueur.verbose){
                                    if(l.rue.numero==1)
                                        System.out.print("\t" + Couleur.CYAN + j + "-" + (j+1) + Couleur.RESET + "(" + (possibilite.size()-1) + ")");
                                    else if(l.rue.numero==2)
                                        System.out.print("\t" + Couleur.JAUNE + j + "-" + (j+1) + Couleur.RESET + "(" + (possibilite.size()-1) + ")");
                                    else
                                        System.out.print("\t" + Couleur.VERT + j + "-" + (j+1) + Couleur.RESET + "(" + (possibilite.size()-1) + ")");
                                }
                            }
                        }
                    }
                }
                if(joueur.verbose)
                    System.out.println();
                return possibilite;
            }*/
    
    /* 
    public boolean possibiliteDansRue(int numero, int rue, int joueur, Jeu j){
        int taille;
        taille = j.joueurs[joueur].ville.rues[rue].taille;
        for(int j2=0; j2<taille; j2++){
            if(j.joueurs[joueur].ville.rues[rue].maisons[])
        }
        switch(rue){
            case 0:
            case 1:
            case 2:
        }
        return false;
    }
*/

    //Choisir au hasard parmi les 3 numéros dispos
    @Override
    public int choixCombinaison(Jeu j, int joueur){
        int res=0;
      

        int numero0 =   ((Travaux) j.numeros[0].top()).getNumero();
        int numero1 =   ((Travaux) j.numeros[1].top()).getNumero();
        int numero2 =   ((Travaux) j.numeros[2].top()).getNumero();

        int rar0= raretéNuméro(numero0);
        int rar1= raretéNuméro(numero1);
        int rar2= raretéNuméro(numero2);
        
        int x ;
        ArrayList<Integer> placeDispo = construirePossibilite1(numero0, joueur, j);
        ArrayList<Integer> placeDispo1 = construirePossibilite1(numero1, joueur, j);
        ArrayList<Integer> placeDispo2 = construirePossibilite1(numero2, joueur, j);
        
       





       //Prioritisation d'action : Bis, Intérimaire, Barrière, Piscine, Lotissement, Parc
       for(int i5=0; i5<3; i5++){
            placeDispo= construirePossibilite1(((Travaux) j.numeros[i5].top()).getNumero(), joueur, j);
            if(((Travaux)j.actions[i5].top()).getAction()==1 && placeDispo.size()!=0){
                    res= i5;      
            }}


// PISCINE 

        for(int i=0; i<3; i++){
            placeDispo= construirePossibilite1(((Travaux) j.numeros[i].top()).getNumero(), joueur, j);
            if(((Travaux)j.actions[i].top()).getAction()==0 && placeDispo.size()!=0){
                    res= i;      
            } }
          
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && (((Travaux) j.numeros[i].top()).getNumero()==8 || ((Travaux) j.numeros[i].top()).getNumero()==9) && j.joueurs[joueur].ville.rues[2].maisons[6].numero==-1){
                res=i;} 

        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && ((Travaux) j.numeros[i].top()).getNumero()==10 && j.joueurs[joueur].ville.rues[1].maisons[7].numero==-1){
                res=i;} 
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && ((Travaux) j.numeros[i].top()).getNumero()==9 && j.joueurs[joueur].ville.rues[0].maisons[6].numero==-1){
                res=i;}
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && ((Travaux) j.numeros[i].top()).getNumero()==10 && j.joueurs[joueur].ville.rues[0].maisons[7].numero==-1){
                res=i;} 
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && ((Travaux) j.numeros[i].top()).getNumero()==11 && j.joueurs[joueur].ville.rues[0].maisons[7].numero==-1){
                res=i;} 
        
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && ((Travaux) j.numeros[i].top()).getNumero()==6 && j.joueurs[joueur].ville.rues[1].maisons[3].numero==-1){
                res=i;} 
        for(int i=0; i<3;i++)
            if(((Travaux)j.actions[i].top()).getAction()==0 && (((Travaux) j.numeros[i].top()).getNumero()==4||((Travaux) j.numeros[i].top()).getNumero()==5) && j.joueurs[joueur].ville.rues[0].maisons[2].numero==-1){
                res=i;} 
                for(int i=0; i<3;i++)
                if(((Travaux)j.actions[i].top()).getAction()==0 && (((Travaux) j.numeros[i].top()).getNumero()==1|| ((Travaux) j.numeros[i].top()).getNumero()==2) && j.joueurs[joueur].ville.rues[1].maisons[0].numero==-1){
                    res=i;} 
            for(int i=0; i<3;i++)
                if(((Travaux)j.actions[i].top()).getAction()==0 && (((Travaux) j.numeros[i].top()).getNumero()==13 ||((Travaux) j.numeros[i].top()).getNumero()==14) && j.joueurs[joueur].ville.rues[2].maisons[10].numero==-1){
                    res=i;
                }
            for(int i=0; i<3;i++)
                if(((Travaux)j.actions[i].top()).getAction()==0 && (((Travaux) j.numeros[i].top()).getNumero()==2 || ((Travaux) j.numeros[i].top()).getNumero()==3) && j.joueurs[joueur].ville.rues[2].maisons[1].numero==-1){
                    res=i;
                }  
     


        
        for(int i5=0; i5<3; i5++){
            placeDispo= construirePossibilite1(((Travaux) j.numeros[i5].top()).getNumero(), joueur, j);
            if(((Travaux)j.actions[i5].top()).getAction()==4 && placeDispo.size()!=0){
                    res= i5;      
            }}
        
            if(!j.joueurs[joueur].ville.barrieres[0][8]){
            for(int i6=0; i6<3; i6++){
                placeDispo= construirePossibilite1(((Travaux) j.numeros[i6].top()).getNumero(), joueur, j);
                if(((Travaux)j.actions[i6].top()).getAction()==5 && placeDispo.size()!=0){
                        res= i6;      
                }
            } }
        
    
        

        if(j.joueurs[joueur].ville.nbParcs[0] != 3 || j.joueurs[joueur].ville.nbParcs[1] != 4 || j.joueurs[joueur].ville.nbParcs[2] != 5 ){
            for(int i8=0; i8<3; i8++){
                placeDispo= construirePossibilite1(((Travaux) j.numeros[i8].top()).getNumero(), joueur, j);
                if(((Travaux)j.actions[i8].top()).getAction()==3 && placeDispo.size()!=0){
                    res= i8; 
                }
            } }

       

        

        int p = findMaximum(rar0, rar1, rar2);
        if(p==rar0 && p>6 && placeDispo.size()!=0)
             res= 0;
        else if(p==rar1&& p>6 && placeDispo1.size()!=0)
             res=1;
        else if(p==rar2 && p>6 && placeDispo2.size()!=0) 
             res=2; 

        /* 
              // Choisi pas de parc si 2 rue ont déjà fait les parc
        placeDispo1 = construirePossibilite1(res, joueur, j);
        //if( (j.joueurs[joueur].ville.nbParcs[0]==3 && (placeDispo1.contains(0)||placeDispo1.contains(1)||placeDispo1.contains(2)||placeDispo1.contains(3)||placeDispo1.contains(4)||placeDispo1.contains(5)||placeDispo1.contains(6)||placeDispo1.contains(7)||placeDispo1.contains(8)||placeDispo1.contains(9)) && (j.joueurs[joueur].ville.nbParcs[1]==4  && (placeDispo1.contains(100)||placeDispo1.contains(101)||placeDispo1.contains(102)||placeDispo1.contains(103)||placeDispo1.contains(104)||placeDispo1.contains(105)||placeDispo1.contains(106)||placeDispo1.contains(107)||placeDispo1.contains(108)||placeDispo1.contains(109)||placeDispo1.contains(110))) )                     ||                    (j.joueurs[joueur].ville.nbParcs[0]==3 && (placeDispo1.contains(0)||placeDispo1.contains(1)||placeDispo1.contains(2)||placeDispo1.contains(3)||placeDispo1.contains(4)||placeDispo1.contains(5)||placeDispo1.contains(6)||placeDispo1.contains(7)||placeDispo1.contains(8)||placeDispo1.contains(9)) && (j.joueurs[joueur].ville.nbParcs[2]==5  && (placeDispo1.contains(200)||placeDispo1.contains(201)||placeDispo1.contains(202)||placeDispo1.contains(203)||placeDispo1.contains(204)||placeDispo1.contains(205)||placeDispo1.contains(206)||placeDispo1.contains(207)||placeDispo1.contains(208)||placeDispo1.contains(209)||placeDispo1.contains(210)||placeDispo1.contains(211))))                        ||                     (j.joueurs[joueur].ville.nbParcs[5]==4 && (placeDispo1.contains(200)||placeDispo1.contains(201)||placeDispo1.contains(202)||placeDispo1.contains(203)||placeDispo1.contains(204)||placeDispo1.contains(205)||placeDispo1.contains(206)||placeDispo1.contains(207)||placeDispo1.contains(208)||placeDispo1.contains(209)||placeDispo1.contains(210)||placeDispo1.contains(211)) && (j.joueurs[joueur].ville.nbParcs[1]==4  && (placeDispo1.contains(100)||placeDispo1.contains(101)||placeDispo1.contains(102)||placeDispo1.contains(103)||placeDispo1.contains(104)||placeDispo1.contains(105)||placeDispo1.contains(106)||placeDispo1.contains(107)||placeDispo1.contains(108)||placeDispo1.contains(109)||placeDispo1.contains(110))))){
        if( ( (j.joueurs[joueur].ville.nbParcs[0]==3 && (placeDispo1.contains(0)||placeDispo1.contains(1)||placeDispo1.contains(2)||placeDispo1.contains(3)||placeDispo1.contains(4)||placeDispo1.contains(5)||placeDispo1.contains(6)||placeDispo1.contains(7)||placeDispo1.contains(8)||placeDispo1.contains(9)) && (j.joueurs[joueur].ville.nbParcs[1]==4  && (placeDispo1.contains(100)||placeDispo1.contains(101)||placeDispo1.contains(102)||placeDispo1.contains(103)||placeDispo1.contains(104)||placeDispo1.contains(105)||placeDispo1.contains(106)||placeDispo1.contains(107)||placeDispo1.contains(108)||placeDispo1.contains(109)||placeDispo1.contains(110))) ) )  ||      (j.joueurs[joueur].ville.nbParcs[0]==3 && (placeDispo1.contains(0)||placeDispo1.contains(1)||placeDispo1.contains(2)||placeDispo1.contains(3)||placeDispo1.contains(4)||placeDispo1.contains(5)||placeDispo1.contains(6)||placeDispo1.contains(7)||placeDispo1.contains(8)||placeDispo1.contains(9)) && (j.joueurs[joueur].ville.nbParcs[2]==5  && (placeDispo1.contains(200)||placeDispo1.contains(201)||placeDispo1.contains(202)||placeDispo1.contains(203)||placeDispo1.contains(204)||placeDispo1.contains(205)||placeDispo1.contains(206)||placeDispo1.contains(207)||placeDispo1.contains(208)||placeDispo1.contains(209)||placeDispo1.contains(210)||placeDispo1.contains(211))))  ||  (j.joueurs[joueur].ville.nbParcs[2]==5 && (placeDispo1.contains(200)||placeDispo1.contains(201)||placeDispo1.contains(202)||placeDispo1.contains(203)||placeDispo1.contains(204)||placeDispo1.contains(205)||placeDispo1.contains(206)||placeDispo1.contains(207)||placeDispo1.contains(208)||placeDispo1.contains(209)||placeDispo1.contains(210)||placeDispo1.contains(211)) )&& (j.joueurs[joueur].ville.nbParcs[1]==4  && (placeDispo1.contains(100)||placeDispo1.contains(101)||placeDispo1.contains(102)||placeDispo1.contains(103)||placeDispo1.contains(104)||placeDispo1.contains(105)||placeDispo1.contains(106)||placeDispo1.contains(107)||placeDispo1.contains(108)||placeDispo1.contains(109)||placeDispo1.contains(110)))){
            for(int i7=0; i7<3; i7++){
                if(((Travaux)j.actions[i7].top()).getAction()==3){
                    res= (i7+1)%3; 
                }
            }
           } */

        // Si la dernière barrière est posée, éviter les barrières
        /*for(int i2=0; i2 <2; i2++){
            if (((Travaux)j.actions[res].top()).getAction()==5 && j.joueurs[joueur].ville.barrieres[1][8])
                res = (res + 1)%3;
            }*/
     
        
        if(res<0 || res>2)
            res=RandomSingleton.getInstance().nextInt(3);

     
       


            // Evite au mieux les BIS, seul cas ou t'as un bis si toutes les combi sont bis
        for(int i2=0; i2 <2; i2++){
            //System.out.println(((Travaux)j.actions[res].top()).getAction()==2);
            //System.out.println(res);
            if (((Travaux)j.actions[res].top()).getAction()==2)
                 res = (res + 1)%3;
            
        }
        

      

        // Vérifie si la carte peut-être posé
        for(int i8=0; i8<3; i8++){
            x = ((Travaux) j.numeros[res].top()).getNumero() ;
            placeDispo = construirePossibilite1(x, joueur, j);
            if(placeDispo.size()==0){
                res = (res+1)%3;
            }
        }


          // Si aucun combinaison plaçable, tenter de prendre intérimaire 
        
              if(placeDispo.size()==0 && placeDispo1.size()==0  && placeDispo2.size()==0 ){
                  for(int i=0; i<3; i++){
                      if(((Travaux)j.actions[i].top()).getAction()==1){
                              res= i;      
                  } } 
              }
          
        //System.out.println("Mon choix de carte est "+res);
        return res; 
    }
    

    // Fonction test brouillon
    public void test(Jeu j, int joueur){
        int numero =((Travaux)j.numeros[0].top()).getNumero();
        System.out.println(numero);
        
    }



    //Choisir de placer un numéro bis
    @Override
    public int choixBis(Jeu j, int joueur, ArrayList<Integer> placeValide){
        int res=-1;


/* 
        if(placeValide.contains(211))
            res = placeValide.indexOf(211);
        if(placeValide.contains(-211))
            res = placeValide.indexOf(-211);
        if(placeValide.contains(210))
            res = placeValide.indexOf(210);
        if(placeValide.contains(-210))
            res = placeValide.indexOf(-210);
        if(placeValide.contains(209))
            res = placeValide.indexOf(209);
        if(placeValide.contains(-209))
            res = placeValide.indexOf(-209);
        if(placeValide.contains(208))
            res = placeValide.indexOf(208);
        if(placeValide.contains(-208))
            res = placeValide.indexOf(-208);
        if(placeValide.contains(207))
            res = placeValide.indexOf(207);
        if(placeValide.contains(-207))
            res = placeValide.indexOf(-207);
        if(placeValide.contains(206))
            res = placeValide.indexOf(206);
        if(placeValide.contains(-206))
            res = placeValide.indexOf(-206);
        if(placeValide.contains(205))
            res = placeValide.indexOf(205);
        if(placeValide.contains(-205))
            res = placeValide.indexOf(-205); 
        if(placeValide.contains(201))
            res = placeValide.indexOf(201);
        if(placeValide.contains(-201))
            res = placeValide.indexOf(-201);
        if(placeValide.contains(-200))
            res = placeValide.indexOf(-200);
        if(placeValide.contains(202))
            res = placeValide.indexOf(202);
        if(placeValide.contains(-202))
            res = placeValide.indexOf(-202);
        if(placeValide.contains(203))
            res = placeValide.indexOf(203);
        if(placeValide.contains(-203))
            res = placeValide.indexOf(-203);
        if(placeValide.contains(204))
            res = placeValide.indexOf(204);
        if(placeValide.contains(-204))
            res = placeValide.indexOf(-204);


*/


        //System.out.println(placeValide);
        //if(j.joueurs[joueur].ville.nbBis==1)
          //  return 0;
       
        if(res<0 || res>placeValide.size()-1)
           res=RandomSingleton.getInstance().nextInt(placeValide.size());
        return res;
    }
    

    int placementRue1(Jeu j, int joueur, int numero, ArrayList<Integer> placeValide){
        if((numero==0||numero==1||numero==2) && placeValide.contains(0)) // Pour 0/1/2 dans chaque rue 
            return placeValide.indexOf(0);
        else if((numero==2) && !placeValide.contains(0) && placeValide.contains(1))
            return placeValide.indexOf(1);
        else if((numero==3||numero==4) && placeValide.contains(1))          // Pour 3/4 dans chaque rue 
            return placeValide.indexOf(1);
        else if((numero==4) && placeValide.contains(2))
            return placeValide.indexOf(2);
        else if((numero==4) && placeValide.contains(3))
            return placeValide.indexOf(3);
        else if((numero==5) && placeValide.contains(2))     
            return placeValide.indexOf(2);
        else if((numero==5) && !placeValide.contains(2) && placeValide.contains(3) && j.joueurs[joueur].ville.rues[0].maisons[2].numero==4)
            return placeValide.indexOf(3);
        else if((numero==6) && placeValide.contains(3))     
            return placeValide.indexOf(3);
        else if((numero==7) && placeValide.contains(4))     
            return placeValide.indexOf(4);
        else if((numero==8) && placeValide.contains(5))     
            return placeValide.indexOf(5);
        else if((numero==9) && placeValide.contains(6))     
            return placeValide.indexOf(6);
        else if((numero==11||numero==10) && placeValide.contains(7))          // Pour 11/10 dans chaque rue 
            return placeValide.indexOf(7);
        else if((numero==10) && placeValide.contains(6))
            return placeValide.indexOf(6);
        else if(numero==13 && placeValide.contains(8))          // Pour 12/13 dans chaque rue 
            return placeValide.indexOf(8);
        else if(numero==12 && placeValide.contains(8) && j.joueurs[joueur].ville.rues[0].maisons[6].numero!=11)          // Pour 12/13 dans chaque rue 
            return placeValide.indexOf(8);
        else if((numero==12) && placeValide.contains(7))
            return placeValide.indexOf(7);
        else if((numero==14||numero==15||numero==16||numero==17) && placeValide.contains(9))     // Pour 14/15/16/17 pour chaque rue
            return placeValide.indexOf(9);
        else if((numero==14) && placeValide.contains(8))
            return placeValide.indexOf(8);
        else if((numero==15) && placeValide.contains(8) && !placeValide.contains(9))
            return placeValide.indexOf(8); 
        else 
            return -1;
        }
    
     int placementRue2(Jeu j, int joueur, int numero, ArrayList<Integer> placeValide){
        if((numero==0||numero==1||numero==2) && !placeValide.contains(1) && placeValide.contains(100))
            return placeValide.indexOf(100);
        else if((numero==2) && !placeValide.contains(100) && placeValide.contains(101))
            return placeValide.indexOf(101);  
        else if((numero==3) && placeValide.contains(101))
            return placeValide.indexOf(101);
        else if((numero==4) && placeValide.contains(102))
            return placeValide.indexOf(102); 
        else if((numero==4) && placeValide.contains(103))
            return placeValide.indexOf(103);  
        else if((numero==5) && placeValide.contains(103) )  //&& (j.joueurs[joueur].ville.rues[1].maisons[2].numero==4 ||j.joueurs[joueur].ville.rues[1].maisons[1].numero==3 )
            return placeValide.indexOf(103);
        else if((numero==5) && placeValide.contains(102))
            return placeValide.indexOf(102);
        else if((numero==6) && placeValide.contains(103))
            return placeValide.indexOf(103);
        else if((numero==7) && placeValide.contains(104))
            return placeValide.indexOf(104);
        else if((numero==8) && placeValide.contains(105))
            return placeValide.indexOf(105);
        else if((numero==9) && placeValide.contains(106))
            return placeValide.indexOf(106);
        else if((numero==10) && placeValide.contains(107))
            return placeValide.indexOf(107);   
        else if((numero==11) && placeValide.contains(108))
            return placeValide.indexOf(108);
        else if((numero==13||numero==12) && placeValide.contains(109))          
            return placeValide.indexOf(109);
        else if((numero==12) && placeValide.contains(108))
            return placeValide.indexOf(108); 
        else if((numero==14||numero==15||numero==16||numero==17) && !placeValide.contains(9) && placeValide.contains(110))
            return placeValide.indexOf(110);
        else if((numero==14) && placeValide.contains(109))
            return placeValide.indexOf(109);
        else if((numero==15) && placeValide.contains(109))
            return placeValide.indexOf(109);
        else 
            return -1; }

     int placementRue3(Jeu j, int joueur, int numero, ArrayList<Integer> placeValide){
        if((numero==0||numero==1||numero==2) && placeValide.contains(200))
            return placeValide.indexOf(200);
        else if((numero==2) && !placeValide.contains(200) && placeValide.contains(201))
            return placeValide.indexOf(201);   
        else if((numero==3||numero==4) && placeValide.contains(201))
            return placeValide.indexOf(201);
        else if((numero==4) && placeValide.contains(203))
            return placeValide.indexOf(203);
        else if((numero==4) && placeValide.contains(202))
            return placeValide.indexOf(202);
        else if((numero==5) && placeValide.contains(202) )
            return placeValide.indexOf(202);
        else if((numero==5) && placeValide.contains(203)  )
            return placeValide.indexOf(203);
        else if((numero==6) && placeValide.contains(203) )
            return placeValide.indexOf(203);
        else if((numero==6) && placeValide.contains(204))
            return placeValide.indexOf(204);
        else if((numero==7) && placeValide.contains(204) && j.joueurs[joueur].ville.rues[2].maisons[7].numero!=8 )
            return placeValide.indexOf(204);
        else if((numero==7) && j.joueurs[joueur].ville.rues[2].maisons[7].numero==8 )
            return placeValide.indexOf(205);
        else if((numero==8) && placeValide.contains(205))
            return placeValide.indexOf(205);
        else if((numero==9) && placeValide.contains(206))
            return placeValide.indexOf(206);
        else if((numero==10) && placeValide.contains(207))
            return placeValide.indexOf(207);   
        else if((numero==11) && placeValide.contains(208))
            return placeValide.indexOf(208); 
        else if((numero==12) && placeValide.contains(209))
            return placeValide.indexOf(209);   
        else if((numero==13) && placeValide.contains(210))
            return placeValide.indexOf(210); 
        else if((numero==14||numero==15||numero==16||numero==17) && placeValide.contains(211))
            return placeValide.indexOf(211);
        else if((numero==14) && placeValide.contains(210))
            return placeValide.indexOf(210);
        else if((numero==15) && placeValide.contains(210))
            return placeValide.indexOf(210);
        else
            return -1; }


    //Choisir au hasard parmi les emplacements dispos
    @Override
    public int choixEmplacement(Jeu j, int joueur, int numero, ArrayList<Integer> placeValide){
        int res=-1;
       
        // Array List
        for(int p : placeValide){
            int rue = p/100;
            int position= p%100;
            int taille_rue= 10+ rue;

            int ind = placeValide.indexOf(p);
            /*System.out.println(p);
            System.out.println(rue);
            System.out.println(position);
            System.out.println(ind);*/
        }
        //System.out.println(placeValide);

        // Nbre de place valide dans chaque rue 
        int r0= 0;
        for(int p : placeValide){
            if((p/100)==0){
                r0 = r0 + 1;
            }
        }
        int r1=0;
        for(int p : placeValide){
            if((p/100)==1){
                r1 = r1 + 1;
            }
        }
        int r2=0;
        for(int p : placeValide){
            if((p/100)==2){
                r2 = r2 + 1;
            }
        }

        //Cas ou parcs sont maxé   
        // 1/2, Rue 3 ou Rue 2   0
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5 && (numero==1 || numero==2) && placeValide.contains(200))
            return placeValide.indexOf(200);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==1 || numero==2) && placeValide.contains(100))
            return placeValide.indexOf(100);
        // 2/3/4, Rue 3 ou Rue 2 1
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5 && (numero == 2 || numero==3 || numero==4) && placeValide.contains(201))
            return placeValide.indexOf(201);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero == 2 ||numero==3 ) && placeValide.contains(101))
            return placeValide.indexOf(101);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero == 4 ) && placeValide.contains(102))
            return placeValide.indexOf(102);
        // 3/4/5, Rue 3 ou Rue 2 2
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==3 || numero==4) && placeValide.contains(202))
            return placeValide.indexOf(202);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==3 || numero==4 || numero ==5) && placeValide.contains(102))
            return placeValide.indexOf(102);
        //3
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==4 || numero==5) && placeValide.contains(203))
            return placeValide.indexOf(203);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero== 6|| numero==4 || numero ==5) && placeValide.contains(103))
            return placeValide.indexOf(103);
        //4
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==6 || numero==5 ||numero==7 ) && placeValide.contains(204))
            return placeValide.indexOf(204);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero== 6|| numero==7 || numero ==5) && placeValide.contains(104))
            return placeValide.indexOf(104);
        //5
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==8 ) && placeValide.contains(205))
            return placeValide.indexOf(205);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero== 8) && placeValide.contains(105))
            return placeValide.indexOf(105);
        //6
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==9 ) && placeValide.contains(206))
            return placeValide.indexOf(206);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero== 9) && placeValide.contains(106))
            return placeValide.indexOf(106);
        //11
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3&& j.joueurs[joueur].ville.nbParcs[2]!=5 && (numero==15|| numero == 14) && placeValide.contains(211))
            return placeValide.indexOf(211);
        //10
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==13|| numero == 14) && placeValide.contains(210))
            return placeValide.indexOf(210);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==15|| numero == 14) && placeValide.contains(110))
            return placeValide.indexOf(110);
        //9
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==13|| numero == 12) && placeValide.contains(209))
            return placeValide.indexOf(209);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==13|| numero == 12) && placeValide.contains(109))
            return placeValide.indexOf(109);
        //8
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5&& (numero==11|| numero == 12) && placeValide.contains(208))
            return placeValide.indexOf(208);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==11|| numero == 12) && placeValide.contains(108))
            return placeValide.indexOf(108);
        //7
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && j.joueurs[joueur].ville.nbParcs[2]!=5 && (numero==10) && placeValide.contains(207))
            return placeValide.indexOf(207);
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==3 && j.joueurs[joueur].ville.nbParcs[0]==3 && (numero==10) && placeValide.contains(107))
            return placeValide.indexOf(107);


            

       
        //if(findMinimum(r0,r1,r2)==r0){   Emplacement pour piscine 
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==2 || numero==3)&& placeValide.contains(201)){
            return placeValide.indexOf(201);}
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==1)&& placeValide.contains(201) && j.joueurs[joueur].ville.rues[2].maisons[0].numero==0){
            return placeValide.indexOf(201);}
            
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==5 || numero==4)&& placeValide.contains(2)){
            return placeValide.indexOf(2); }
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==9)&& placeValide.contains(6)){
            return placeValide.indexOf(6);}
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==10 || numero==11)&& placeValide.contains(7)){
            return placeValide.indexOf(7);}

        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==1 || numero==2 ||numero==0)&& placeValide.contains(100)){
            return placeValide.indexOf(100);}
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==3)&& placeValide.contains(100) && (j.joueurs[joueur].ville.rues[1].maisons[1].numero==4||j.joueurs[joueur].ville.rues[1].maisons[2].numero==5)){
            return placeValide.indexOf(100);}
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==6)&& placeValide.contains(103)){
            return placeValide.indexOf(103); }
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==5)&& placeValide.contains(103) && j.joueurs[joueur].ville.rues[1].maisons[1].numero!=4 && j.joueurs[joueur].ville.rues[1].maisons[0].numero!=3){
            return placeValide.indexOf(103); }
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==10||numero==11)&& placeValide.contains(107)){
            return placeValide.indexOf(107);}

        
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==8 || numero==9)&& placeValide.contains(206) ){
            return placeValide.indexOf(206); }
        if(((Travaux)j.actions[choixCombinaison(j, joueur)].top()).getAction()==0 && (numero==14 || numero==13)&& placeValide.contains(210)){
            return placeValide.indexOf(210);}
       



      

            // Emplacement par défaut 


        res = placementRue1(j, joueur, numero, placeValide);
        if(res==-1){
            res= placementRue2(j, joueur, numero, placeValide);
            if(res==-1)
                res = placementRue3(j, joueur, numero, placeValide);
        }
      
        if(j.plans[0].pointsPremier == 10 ){
            res = placementRue3(j, joueur, numero, placeValide);
            if(res==-1){
                res= placementRue1(j, joueur, numero, placeValide);
                if(res==-1)
                    res = placementRue2(j, joueur, numero, placeValide);
            }
        }
        //au cas ou ya des bis qui décalent

        if((numero==3) && !placeValide.contains(202) && placeValide.contains(203))
            return placeValide.indexOf(203);
        else if((numero==3) && !placeValide.contains(102) && placeValide.contains(103))
            return placeValide.indexOf(103);

        else if((numero==3) && !placeValide.contains(2) && placeValide.contains(3))
            return placeValide.indexOf(3);

        else if((numero==4) && !placeValide.contains(203) && placeValide.contains(204))
            return placeValide.indexOf(204);

        else if((numero==4) && !placeValide.contains(103) && placeValide.contains(104))
            return placeValide.indexOf(104);

        else if((numero==4) && !placeValide.contains(3) && placeValide.contains(4))
            return placeValide.indexOf(4);

        else if((numero==5) && !placeValide.contains(204) && placeValide.contains(205))
            return placeValide.indexOf(205);

        else if((numero==5) && !placeValide.contains(104) && placeValide.contains(105))
            return placeValide.indexOf(105);

        else if((numero==5) && !placeValide.contains(4) && placeValide.contains(5))
            return placeValide.indexOf(5);

        else if((numero==6) && !placeValide.contains(205) && placeValide.contains(206))
            return placeValide.indexOf(206);

        else if((numero==6) && !placeValide.contains(105) && placeValide.contains(106))
            return placeValide.indexOf(106);

        else if((numero==6) && !placeValide.contains(5) && placeValide.contains(6))
            return placeValide.indexOf(6);

        

        else if((numero==7) && !placeValide.contains(206) && placeValide.contains(207))
            return placeValide.indexOf(207);

        else if((numero==7) && !placeValide.contains(106) && placeValide.contains(107))
            return placeValide.indexOf(107);

        else if((numero==7) && !placeValide.contains(6) && placeValide.contains(7))
            return placeValide.indexOf(7);

        

        else if((numero==8) && !placeValide.contains(207) && placeValide.contains(208))
            return placeValide.indexOf(208);

        else if((numero==8) && !placeValide.contains(107) && placeValide.contains(108))
            return placeValide.indexOf(108);

        else if((numero==8) && !placeValide.contains(7) && placeValide.contains(8))
            return placeValide.indexOf(8);

        

        else if((numero==9) && !placeValide.contains(208) && placeValide.contains(209))
            return placeValide.indexOf(209);
        else if((numero==9) && !placeValide.contains(108) && placeValide.contains(109))
            return placeValide.indexOf(109);
        else if((numero==9) && !placeValide.contains(8) && placeValide.contains(9))
            return placeValide.indexOf(9);


      
            

        //A COMPLETER
        
        if(res<0 || res>placeValide.size()-1)
            res=RandomSingleton.getInstance().nextInt(placeValide.size());
        return res;
    }
    
    //Choisir le même numéro que celui de la carte quand l'action est un intérimaire
    @Override
    public int choixNumero(Jeu j, int joueur, int numero){
        int res=-1;  // =0 pour ne rien choisir

        if(numero==1)
            res=0;
        if(numero==15)
            res=17;
        else
            res= numero;

        ArrayList<Integer> placeDisp = construirePossibilite1( numero,  joueur,  j);
        if(placeDisp.size()==0){
            int j3 = numero-3;
            int[] inter = new int[5];
            int ind=0;
            for(j3++; j3<(numero+3); j3++){
                

                if(construirePossibilite1(j3,joueur,j).size()==0){
                    inter[ind]=0;}
                else{
                    inter[ind]=j3;
                }
            //System.out.println(j3);
            ind ++;
            }
            boolean swapped = true;
            while (swapped) {
                swapped = false;
                for (int i = 1; i < inter.length; i++) {
                    if (inter[i] < inter[i - 1]) {
                        // échanger inter[i] et inter[i - 1]
                        int temp = inter[i];
                        inter[i] = inter[i - 1];
                        inter[i - 1] = temp;
                        swapped = true;
                    }
                }
            }
            /*System.out.println("Tableau trié : ");
            for (int i : inter) {
            System.out.print(i + " "); */
            res = inter[4]; 
        }
        //A COMPLETER
        //System.out.println(res);
        if((res<(numero-2) || res>(numero+2)) || res<0)
            res=Math.max(0, RandomSingleton.getInstance().nextInt(5) + numero - 2) ;

        //System.out.println(res);
        return res;
    }
    
    //Valorise aléatoirement une taille de lotissements (proba plus forte si plus d'avancements possibles)
    @Override
    public int valoriseLotissement(Jeu j, int joueur){        
        int res=-1;

        //A COMPLETER
        res=6;
        if(j.joueurs[joueur].ville.avancementPrixLotissement[5]==3 && j.joueurs[joueur].ville.avancementPrixLotissement[0]==0){
            res= 1;
        }
     
        if(j.joueurs[joueur].ville.avancementPrixLotissement[5]==4){
            res= 4;
        }
      
        
        if(res<1 || res>6)
            res=RandomSingleton.getInstance().nextInt(6)+1;
        return res;
    }
    
    //Met une barrière à une position aléatoire
    @Override
    public int choixBarriere(Jeu j, int joueur,  ArrayList<Integer> placeValide){
        int res=-1;
        // System.out.println(placeValide);
        //A COMPLETER
        res = 25;
        if(placeValide.size()==30)
            res=15;
        if(placeValide.size()==29)
            res=6;
        if(placeValide.size()==28) //Ok
            res=14;
        if(placeValide.size()==27) //Plus ok
            res=16;
        if(placeValide.size()==26)
            res=15;
        if(placeValide.size()==25)
            res=14;
        if(placeValide.size()==24)
            res=8;
        if(placeValide.size()==23)
            res=7;
        if(placeValide.size()==22)
            res=6;
        if(placeValide.size()==21)
            res=15;
        if(placeValide.size()==20)
            res=14;
        if(placeValide.size()==19)
            res=13;
        if(placeValide.size()==18)
            res=12;
        if(placeValide.size()==17)
            res=11;
            

        if(res<0 || res>placeValide.size()-1)
            res=RandomSingleton.getInstance().nextInt(placeValide.size());
        return res;
    }
    
    //Valide toujours un plan
    @Override
    public boolean validePlan(Jeu j, int joueur, int plan) {
        boolean res = true;
        
        //A COMPLETER
        
        return res;
    }
    
    @Override
    public void resetStrat(){};
}
