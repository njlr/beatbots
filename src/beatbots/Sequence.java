package beatbots;

public class Sequence {

	private Beat[] sequence;
	private Beat[] recording;
	
	public Sequence() {
		
		super();
		
		this.sequence = new Beat[Metronome.BEATS_PER_BAR];
		this.recording = new Beat[Metronome.BEATS_PER_BAR];
		
		for (int i = 0; i < Metronome.BEATS_PER_BAR; i++) {
			
			this.sequence[i] = Beat.White;
			this.recording[i] = Beat.White;
		}
		
		// TODO: Proper generation or loading
		this.sequence[0] = Beat.Red;
		this.sequence[1] = Beat.Blue;
		this.sequence[2] = Beat.White;
		this.sequence[3] = Beat.Blue;
		this.sequence[4] = Beat.White;
		this.sequence[5] = Beat.Red;
		this.sequence[6] = Beat.Red;
		this.sequence[7] = Beat.Blue;
	}
	
	public Beat getFromSequence(int index) {
		
		return this.sequence[index];
	}
	
	public void record(int index, Beat beat) {
		
		this.recording[index] = beat;
	}
	
	public Beat playback(int index) {
		
		return this.recording[index];
	}
}
