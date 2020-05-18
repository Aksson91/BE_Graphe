package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.utils.*;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.AbstractSolution.Status;

import java.util.Collections;
import java.util.ArrayList;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	private float Finalcost; //cte coutfinal
	
	
	protected Label newLabel(Node node, ShortestPathData data) {
		return new Label(node); 
	} //methode création de label
	
	public float getCoutFinal() { //getter 
	    	return this.Finalcost;
	}

	//constructeur
    public DijkstraAlgorithm(ShortestPathData data) { 
        super(data);
        this.Finalcost = 0;
    } 
    

    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData(); //creation de la variable data correspondant au graph de plus courts chemins
        ShortestPathSolution solution = null; //solution que l'on retournera a la fin du dijkstra ; initialisé null
        BinaryHeap<Label> tasDeLabels = new BinaryHeap<Label>(); //tas de labels
        Label tableauDeLabels[] = new Label[data.getGraph().size()]; //tableau de labels
        boolean resultatFinal = false; //booleen pour arrêter la boucle while
        
        //phase initialisation du label 
        
        tableauDeLabels[data.getOrigin().getId()] = newLabel(data.getOrigin(), data); //on met le label correspondant au noeud d'origine du graph dans la premiere case du tableau 
        tasDeLabels.insert(tableauDeLabels[data.getOrigin().getId()]); //on insere dans la tas de label le label correspond au noeud origine 
        tableauDeLabels[data.getOrigin().getId()].setInTas(); //le label est maintenant dans le tas donc setintas
        tableauDeLabels[data.getOrigin().getId()].setCost(0); //on met le cout à 0
        
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        Arc[] predecessorArcs = new Arc[data.getGraph().size()]; //tab d'arcs 
             
        while((tasDeLabels.isEmpty() == false) && (resultatFinal == false)) { 
        	Label current = tasDeLabels.deleteMin(); //le label actuel correspond au plus petit cout
        	notifyNodeMarked(current.getNode());  // Notify observers le noeud est marqué
        	current.setMark(); // on marque le label actuel
        	
        	// check si le noeud du label actuel correspond au noeud destination
        	if (current.getNode() == data.getDestination()) {
        		Finalcost = current.getCost(); //cout final correspond au cout du label actuel
        		resultatFinal = true; //on sort du while
        	}
        	
        	for(Arc arc : current.getNode().getSuccessors()) { 
        		
                // Small test to check allowed roads...
        		if (!data.isAllowed(arc)) {
        			continue;
        		}
        		
        		if (tableauDeLabels[arc.getDestination().getId()] == null) {
        			notifyNodeReached(arc.getDestination()); // Notify observers le label du noeud destination est crée
        			tableauDeLabels[arc.getDestination().getId()] = newLabel(arc.getDestination(), data); //label destination
        		}

        		if (tableauDeLabels[arc.getDestination().getId()].getMark() == false) { //si le label destination n'est pas encore marqué
        			
        			//mettre a jour le cout
       			
        			if (tableauDeLabels[arc.getDestination().getId()].getCost() > current.getCost() + data.getCost(arc)) {
        				tableauDeLabels[arc.getDestination().getId()].setCost(current.getCost() + (float) data.getCost(arc));
        				
        				tableauDeLabels[arc.getDestination().getId()].setFather(current.getNode()); // set le noeud pere
        				
        				//si le label est dans le tas on l'enleve du tas sinon on le met dans le tas
        				if (tableauDeLabels[arc.getDestination().getId()].getInTas()) { 
        					tasDeLabels.remove(tableauDeLabels[arc.getDestination().getId()]);	
        				}
        				else tableauDeLabels[arc.getDestination().getId()].setInTas();
        					
        				tasDeLabels.insert(tableauDeLabels[arc.getDestination().getId()]);
        				
        				//mise a jour du tab d'arcs
        				predecessorArcs[arc.getDestination().getId()] = arc; 
        			}
        			
        		}
        		
        	}
        	
        }
        
        // Si la destination n'a pas de prédecesseur, la solution n'est pas faisable...
        if (predecessorArcs[data.getDestination().getId()] == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {
			// sinon => la destination a des prédecesseurs
            // La destination a été trouvé, on notifie les observers
			
			notifyDestinationReached(data.getDestination());
		      // Create the path from the array of predecessors...
			// Création du chemin du tableau de prédecesseurs 
			ArrayList<Arc> arcs = new ArrayList<Arc>();	
			Arc arc = predecessorArcs[data.getDestination().getId()];

			while (arc != null) {
				arcs.add(arc);
				arc = predecessorArcs[arc.getOrigin().getId()];
			}
			
			 // Inverse le chemin pour le mettre dans l'ordre origine vers destination
			Collections.reverse(arcs);
			
			//création de la solution finale
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));
			
		}
        
        return solution;
    }

}
