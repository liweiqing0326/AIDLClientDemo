package lwq.com.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
    public String title;
    public String artist;
    public int duration;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeInt(this.duration);
    }

    public Music() {
    }

    protected Music(Parcel in) {
        this.title = in.readString();
        this.artist = in.readString();
        this.duration = in.readInt();
    }

    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                '}';
    }
}
