package project1;

import java.util.Iterator;

//import java.util.Iterator;

public class MusicLinkedList implements MusicList {
	// Link Head and tail

	//duration = numof Samples / sample rate
	private Link head;
	private Link tail;

	//info for the List
	private float sampleRate;
	private int channels;
	private int numOfSamples;

	MusicLinkedList( float sampleRate,int channels){
		//basic head and tail nodes. Starting point of list
		this.head = null;
		this.tail = head;

		//initial point of all the information
		this.setSampleRate(sampleRate);
		this.setChannels(channels);

		//to be update while new nodes are added
		this.numOfSamples = 0;
	}
	/*setters for channels*/
	public void setChannels(int channels) {
		this.channels = channels;
	}

	public Link getHead(){
		return head;
	}
	
	public Link getTail(){
		return tail;
	}
	
	
	
	
	
	
	/* getter and setters for sample rates*/
	public float getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(float sampleRate) {
		this.sampleRate = sampleRate;
	}

	@Override
	public int getNumChannels() {
		// TODO Auto-generated method stub
		return channels;
	}
	@Override
	public int getNumSamples() {
		return numOfSamples;
	}
	@Override
	public float getDuration() {
		// TODO Auto-generated method stub
		//System.out.println("num of Samples is: "+numOfSamples);
		float duration = numOfSamples/sampleRate;
		System.out.println("duration is "+ duration);
		return duration;
	}
	@Override
	public void addEcho(float delay, float percent) {
		
		float startDelay = delay * sampleRate;
		Link StartDelayNode = head;
		for(int i = 0 ; i < startDelay; i++){
			StartDelayNode = StartDelayNode.nextsample;
		}
	
		
		Link HeadPlaceMarkSample = head;
		
		
		while(StartDelayNode!=null){
			Link HeadPlaceMarkChannel = HeadPlaceMarkSample;
			Link StartDelayNodeChannel=StartDelayNode;
			while(StartDelayNodeChannel != null){
				
				StartDelayNodeChannel.sample = StartDelayNodeChannel.sample + HeadPlaceMarkChannel.sample * percent;
				
				StartDelayNodeChannel =StartDelayNodeChannel.nextChannel;
				HeadPlaceMarkChannel = HeadPlaceMarkChannel.nextChannel;
			}
			StartDelayNode = StartDelayNode.nextsample;
			HeadPlaceMarkSample= HeadPlaceMarkSample.nextsample;
			
		}

	}
	@Override

	public void reverse() {
		
		ListStack stack = new ListStack();
		Link curr = head;
		
		while(curr!= null){
			Link PlaceHolder= curr;
			Link placeHolderIter = PlaceHolder;
			
			curr=curr.nextsample;
			
			while( placeHolderIter!=null){
				placeHolderIter.nextsample=null;
				placeHolderIter=placeHolderIter.nextChannel;
			}
			
			stack.push(PlaceHolder);	
		}
		Link Thead=null;;
		Link Ttail=Thead;
		while(stack.empty()==false){
			Link newColumn = (Link) stack.pop();
			if(Thead==null){
				
				Thead = newColumn;
				Ttail = Thead;
			}
			else{
				Link Tcurr = Ttail;
				Link Tcolumn = newColumn;
				while(Tcolumn != null ){
					
					Tcurr.nextsample = Tcolumn;
					
					//System.out.println("tcolumn: "+ Tcolumn.sample);
					Tcolumn = Tcolumn.nextChannel;
					Tcurr = Tcurr.nextChannel;
				}
				Ttail = newColumn;
				//System.out.println("curr.datais " );
				
			}
			
		}
		head=Thead;
		//print();
		
			
	}
	
	@Override
	public void changeSpeed(float percentChange) {
		sampleRate = sampleRate *percentChange;

	}
	@Override
	public void changeSampleRate(float newRate) {
//		MusicList ml =new MusicLinkedList(newRate,channels); 
//		
//		Iterator<float[]> oldIter = this.iterator();
//		ml.addSample(oldIter.next());;
//		
//		double newNumOfNodes = newRate*getNumSamples();
//		
//		double newTimeBetweenNewNodes = (double)1/(double)newRate;
//		
//		double OldTimeBetweenNodes = (double)1/ (double)sampleRate;
//		
//		double totalTime = getDuration() * newNumOfNodes;
//
//		double TimeSinceNewNodeAdded = 0;
//		
//		Link temp = head;
//		Link tempPrev = temp;
//		double s1Time = 0 ;
//		double s2Time = OldTimeBetweenNodes;
//		for(double index = 0 ; index <= totalTime; index+=newTimeBetweenNewNodes){
//			TimeSinceNewNodeAdded +=newTimeBetweenNewNodes;
//			//System.out.println("inf     hgjyhinity");
//			while(TimeSinceNewNodeAdded < s2Time ){
//				tempPrev=temp;
//				temp= temp.nextsample;
//				s1Time += OldTimeBetweenNodes;
//				s2Time += OldTimeBetweenNodes;
//				System.out.println("ijjjnf ");
//			}
//			
//			double a = TimeSinceNewNodeAdded -s1Time;
//			double b = s2Time- TimeSinceNewNodeAdded;
//			double c = OldTimeBetweenNodes;
//			
//			float[] inturpulatedValues = new float[channels];
//			Link tempUp =temp;
//			Link prevUp = tempPrev;
//			
//			for(int i = 0 ; i<channels ; i++){
//				double interpalate = (prevUp.sample*b/c) + (tempUp.sample*a/c);
//				inturpulatedValues[i] = (float)interpalate;
//				tempUp=tempUp.nextChannel;
//				prevUp = prevUp.nextChannel;
//			}
//			ml.addSample(inturpulatedValues);
//		}
//		head=null;
//		tail=null;
//		Iterator<float[]> mlIter=  ml.iterator();
//		for(int i =0 ; i < newNumOfNodes ; i++){
//			this.addSample(mlIter.next());
//		}

	}
	
	
	
	
	
	@Override
	public void addSample(float sample) {
		float[] singleSample;
		singleSample = new float [1];
		singleSample[0]=sample;
		System.out.println("stuff happens");
		addSample(singleSample);
	}
	@Override
	public void addSample(float[] sample) {

		//System.out.println("channels is " + channels +"//"+ "sample length = "+sample.length);
		Link tempH=null;
		Link tempT=tempH;
		//System.out.println("get num channel  is: " + getNumChannels());
		//creaating the new Sample
		for(int i = 0; i<channels; i++ ){
			Link temp = new Link();
			temp.setSample(sample[i]);
			//numOfSamples++;


			if(tempH == null){
				tempH = tempT = temp;
			}
			else{
				tempT.nextChannel=temp;
				tempT=tempT.nextChannel;
			}

		}
		if(head== null){
			head = tail = tempH;
		}else{
			Link curr=tail;
			Link currT = tempH;
			while(curr !=null){
				curr.nextsample=currT;
				curr=curr.nextChannel;
				currT=currT.nextChannel;
			}
			tail=tempH;
		}
		numOfSamples++;

	}
	// TODO Auto-generated method stub
	@Override
	public Iterator<float[]> iterator() {
		InnerIterator iter = new InnerIterator();

		return iter;
	}

	@Override
	public Iterator<Float> iterator(int channel) {
		SingleInnerIterator sitr= new SingleInnerIterator(channel);
		return sitr;

	}


	@Override
	// TODO clip
	public void clip(float startTime, float duration) {
		
		float timeBetweenSamples= 1/sampleRate;
		System.out.println("time between sample Rate "+ timeBetweenSamples);
		System.out.println("start time is "+ startTime);
		System.out.println("duration is: " + duration);
		
		float beginningSample = startTime * sampleRate;
		float endSample = (startTime+ duration) * sampleRate;
		
		//System.out.println("beginning sample  is: " + beginningSample +"\n" + "end Point is: " + endSample);
		int i = 1;
		int e = 1;
		beginningSample = beginningSample/i;
		 i = (int) (beginningSample/1);
		 e =(int) (endSample/1);
		//System.out.println("beginning sample  after division by 1 is: " + "\n"  + "end Point after division by  1 is: " + e);
		
		
		Link StartingSample= head;
		Link followStart=null;
		Link EndPoint=head;
		Link FollowEnd= null;
		for( int index = 0 ; index < i ; index++){
			followStart = StartingSample;
			StartingSample = StartingSample.nextsample;
		}
	//  Followend should start where starting sample is
		FollowEnd = StartingSample;
		for( int index = (int) beginningSample;  index < endSample ; index++){
			
			FollowEnd = FollowEnd.nextsample;			
		}
		 Link rowIter= FollowEnd ;
		while(rowIter!=null){
			rowIter.nextsample=null;
			rowIter=rowIter.nextChannel;
			
		}
		head = followStart;
		tail = FollowEnd; 
		
		

	}

	
	@Override
	public void spliceIn( float startSpliceTime, MusicList clipToSplice) {
		float beginningSample = startSpliceTime * sampleRate;
		int i = 1;
		beginningSample = beginningSample/i;
		
		Link current = head;
		Link endOfSplicePlaceHolder;
		Link TailPlaceHolder = tail;

		
		 i = (int) (beginningSample/1);
		 for(int index=0 ;index < beginningSample; index++){
			 current=current.nextsample;
		 }
		 tail = current;
		 endOfSplicePlaceHolder = tail.nextsample;
		 
		 Iterator<float[]> clipIter = clipToSplice.iterator();
		 for(int index =0 ; index< clipToSplice.getNumSamples() ; index++){
			 float[] l1 = clipIter.next();
			 addSample(l1);
		 }
		 Link temp = tail;
		 while(temp!=null){
			 temp.nextsample=endOfSplicePlaceHolder;
			 temp=temp.nextChannel;
			 endOfSplicePlaceHolder = endOfSplicePlaceHolder.nextsample;
		 }
	}
	@Override
	public void makeMono(boolean allowClipping) {

		float max = 0;
		if (allowClipping==false){
			Link curr = head;
			while(curr != null){
				float tempMax=0;
				Link temp=curr;
				for(int i=0 ; i<channels; i++){
					tempMax+=temp.sample;
					temp=temp.nextChannel;
				}
				if(max<tempMax){
					max=tempMax;
				}
				curr.nextChannel=null;
				curr=curr.nextsample;
			}
			curr=head;
			while( curr!=null){
				curr.sample=curr.sample/max;
				curr=curr.nextsample;
			}

		}else{
			Link curr = head;
			while(curr != null){
				float tempVal=0;
				Link temp=curr;
				for(int i=0 ; i<channels; i++){
					tempVal+=temp.sample;
					temp=temp.nextChannel;
				}
				if(tempVal<-1){
					tempVal=-1;
				}
				if(tempVal>1){
					tempVal=1;
				}
				curr.sample=tempVal;
				curr.nextChannel=null;
				curr=curr.nextsample;
			}

		}

	}
	@Override
	public void combine(MusicList clipToCombine, boolean allowClipping) {

		// TODO Auto-generated method stub
		Link curr=head;
		Iterator<float[]> clipIter = clipToCombine.iterator();

		if(allowClipping){

			while( curr!=null ){
				float[] clipSamples = clipIter.next();
				//System.out.println("clipSample.lenght = :  "+ clipSamples.length+"\n");
				Link temp = curr;
				for(int index = 0; index < clipSamples.length ;index++){
					//System.out.println("clip Sample at ["+index+"]" +"is: " +  clipSamples[index]);


					float tempsound= temp.sample + clipSamples[index];
					//System.out.println("tempsound before else is "+ tempsound);

					if(tempsound < -1){


						tempsound = -1;
					}
					if(tempsound > 1){
						tempsound = 1;
					}

					//System.out.println("tempsound after else is "+ tempsound);
					temp.setSample(tempsound);

					//System.out.println("temp.sample is "+ temp.sample);

					temp=temp.nextChannel;

					//System.out.println("\n for loop run \n");
				}
				//System.out.println("\n while loop run \n");

				curr = curr.nextsample;
			}


		}else{
			float max=0;
			Link tempSample = head;
			while(tempSample != null){
				Link TempChan =  tempSample;
				float[] clipSamples = clipIter.next();
				
				int i= 0;
				while(TempChan != null){
					TempChan.sample=TempChan.sample + clipSamples[i];
					if(max< TempChan.sample ){
						max=TempChan.sample;
					}
					TempChan = TempChan.nextChannel;
					i++;
					//System.out.println("infinity");
				}
				tempSample = tempSample.nextsample;
			}
			tempSample = head;
			while(tempSample != null){
				Link TempChan =  tempSample;
				while(TempChan != null){
					TempChan.sample=TempChan.sample/max;
					TempChan = TempChan.nextChannel;
				}
				tempSample=tempSample.nextsample;
			}
		}
	}
	@Override
	public MusicList clone() {
		MusicList clone =new MusicLinkedList(sampleRate,channels);
		Iterator<float[]> originalIter = iterator();
		for(int counter = 0 ; counter < numOfSamples ; counter ++){
			float[] orgIterList = originalIter.next();
			clone.addSample(orgIterList);
			
		}
		return clone;
	}

	public void print(){
		Link currChannel = head;
		Link currSample =head;
		int sampNum=0;
		while(currSample!= null){
			System.out.println("current samp" + sampNum);
			while(currChannel != null){
				System.out.println("channel Data "+currChannel.sample);
				currChannel=currChannel.nextChannel;
			}
			currSample=currSample.nextsample;
			currChannel=currSample;
			sampNum++;
		}
		System.out.println("number of Sample " + getNumSamples() );
		System.out.println("number of channels " + channels );
	}


	// Link Class

	public class Link{
		private float sample;
		private Link nextChannel;
		private Link nextsample;
		Link(){
			this.nextChannel = null;
			this.nextsample = null;
		}

		public float getSample() {
			return sample;
		}
		public void setSample(float sample) {
			this.sample = sample;
		}
		public Link getNextChannel() {
			return nextChannel;
		}
		public void setNextChannel(Link nextChannel) {
			this.nextChannel = nextChannel;
		}
		public Link getNextSample() {
			return nextsample;
		}
		public void setNextSample(Link next) {
			this.nextsample = next;
		}


	}
	//get one sample


	public class InnerIterator implements Iterator<float[]>{
		Link currentSample = head;
		//		Link currentChannel = currentSample;


		@Override
		public boolean hasNext() {
			if(currentSample.nextsample!= null){
				return true;
			}
			return false;
		}

		@Override
		public float[] next() {
			float[] arrayOfChannelVal = new float[getNumChannels()];
			int i = 0;


			//1. preparation: create an array to store all the values of each channel of one column
			//2. create a tmp equal to the current head
			//3. using tmp to go through the column (going up)
			//4. make currentSample equal to currentSample.nextSample
			//return

			if( currentSample != null ){
				Link temp;
				temp = currentSample;
				while(temp!= null){
					arrayOfChannelVal[i] = temp.sample;
					i++;
					temp = temp.nextChannel;
				}
			}
			if(currentSample.nextsample!=null){
				currentSample=currentSample.nextsample;
			}
			return arrayOfChannelVal ;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}



	}


	public class SingleInnerIterator implements Iterator<Float>{
		Link current = head;
		int index;

		SingleInnerIterator(int indexForChan){
			index= indexForChan;
			for(int i = 0 ; i< indexForChan ; i++)
				current=current.nextChannel;
		}
		public boolean hasNext() {
			if(current.getNextSample() != null){
				return true;
			}
			return false;
		}

		@Override
		public Float next() {
			float val=current.getSample();
			current=current.nextsample;
			return val;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
	}


	public class ListStack implements Stack{
		private Link top;

		public ListStack() 
		{
			top = null;
		}

		public void push(Object elem) 
		{
			top = new Link(elem, top);
		}

		public boolean empty() 
		{
			return top == null;
		}

		public Object pop() 
		{
			Object poppedvalue;

			if (top == null)
				return null;
			poppedvalue = top.element();
			top = top.next();
			return poppedvalue;
		}

		public String toString()
		{
			Link stackPtr = top;
			String result = "[";
			if (stackPtr != null){
				result = result +  top.element();
				for (stackPtr = stackPtr.next; stackPtr != null; stackPtr = stackPtr.next)
				{
					result = result + "," + stackPtr.element();
				}

			}
			result = result + "]";
			return result;
		}

		private class Link 
		{
			private Object element;
			private Link next;
			
			public Link(Object newelement) 
			{
				element = newelement;
				next = null;
			}

			public Link(Object newelement, Link newnext) 
			{
				element = newelement;
				next = newnext;
			}

			public Link next() 
			{
				return next;
			}

			public Object element() 
			{
				return element;
			}

			public void setNext(Link newnext) 
			{
				next = newnext;
			}

			public void setElement(Object newelement) 
			{
				element = newelement;
			}
		}    


	}
}






