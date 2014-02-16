package turtlekit.mle;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import madkit.kernel.Activator;
import turtlekit.cuda.CudaEngine;
import turtlekit.cuda.CudaGPUGradientsPhero;
import turtlekit.cuda.CudaPheromone;
import turtlekit.cuda.CudaPheromoneV3;
import turtlekit.kernel.TKEnvironment;
import turtlekit.pheromone.Pheromone;

public class MLEEnvironment extends TKEnvironment{
	
	@Override
	protected void activate() {
		super.activate();
		getPheromone(AbstractMLEAgent.PRE+"0", 20, 64); 
//		getPheromone(AbstractMLEAgent.ATT+"0", 0, 0); 
//		getPheromone(AbstractMLEAgent.REP+"0", 0, 0);

		getPheromone(AbstractMLEAgent.PRE+"1", 40, 50); 
		getPheromone(AbstractMLEAgent.ATT+"1", 87, 100); 
		getPheromone(AbstractMLEAgent.REP+"1",97, 100);
		
		getPheromone(AbstractMLEAgent.PRE+"2", 15, 100); 
		getPheromone(AbstractMLEAgent.ATT+"2", 86, 100); 
		getPheromone(AbstractMLEAgent.REP+"2",96, 100);
		
		getPheromone(AbstractMLEAgent.PRE+"3", 10, 100); 
		getPheromone(AbstractMLEAgent.ATT+"3", 85, 100); 
		getPheromone(AbstractMLEAgent.REP+"3", 92, 100);
//		
		getPheromone(AbstractMLEAgent.PRE+"4", 15, 100); 
		getPheromone(AbstractMLEAgent.ATT+"4", 84, 100); 
		getPheromone(AbstractMLEAgent.REP+"4",89, 100);
		getPatch(0,0);

	}
	
//	@Override
//	protected void update() {
//		super.update();
//		for (Pheromone p : getPheromones()) {
//			((CudaPheromoneV3) p).updateV3();
//		}
//	}
	
	protected Pheromone createCudaPheromone(String name, int evaporationPercentage, int diffusionPercentage){
		if(GPU_GRADIENTS && ! name.contains("PRE"))
			return new CudaGPUGradientsPhero(name, getWidth(), getHeight(), evaporationPercentage, diffusionPercentage);
		return new CudaPheromone(name, getWidth(),	getHeight(), evaporationPercentage, diffusionPercentage);
	}
	

}
