import java.util.ArrayList;
import java.lang.Math;

class Playlist {
    ArrayList<String> playlistSongs;
    int currentSong = 0;
    public Playlist(ArrayList<String> songs) {
        if (songs.size() == 0) System.out.println("Your playlist needs at least 1 song.");
        else playlistSongs = songs;
    }

    public void printNextSong() {
        System.out.println(playlistSongs.get(currentSong));
        currentSong++;
    }

    public void shuffle() {
        for (String song : this.playlistSongs) {
            int randomIndex = (int)(Math.random() * this.playlistSongs.size()) + 1;
            if (this.playlistSongs.get(randomIndex) == song) continue;
            else this.playlistSongs.set(randomIndex, song);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> songs = new ArrayList<String>();
        songs.add("Lemon Tree");
        songs.add("Diamonds");
        songs.add("Boulevard of Broken Dreams");

        Playlist myPlaylist = new Playlist(songs);
        myPlaylist.printNextSong();
        myPlaylist.shuffle();
        System.out.println(myPlaylist.playlistSongs);
    }
}
