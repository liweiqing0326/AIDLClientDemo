// IMusicPlayer.aidl
package lwq.com.aidlserver;

import lwq.com.aidlserver.Music;

interface IMusicPlayer {

	void play();

	void pause();

	int getDuration();

	Music getMusic();

}