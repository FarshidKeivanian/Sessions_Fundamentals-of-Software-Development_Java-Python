class Bird:
    def make_sound(self):
        print("Tweet!")

class Emu(Bird):
    def make_sound(self):
        print("Grunt!")

def speak(bird):
    bird.make_sound()

speak(Bird())   # Output: Tweet!
speak(Emu())    # Output: Grunt!
