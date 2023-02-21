# Install Process

For general install process, see here:
https://github.com/Gremious/StS-DefaultModBase/wiki/Step-1:-Setting-Up-Your-Integrated-Development-Environment-and-the-Default-Mod-Base

However, some of those directions are a little stale.  Below are some updated notes

---

# Step 1

- Copy source folder to new folder (rename as necessary)

- Within IntelliJ
  - Open (select root project folder)
  - Add new Maven project
    - Shift Shift / search on "Maven"
    - Add new Maven project
    - Select <i>theDefault</i> folder
    - Choose View -> Tol Windows -> Maven
    - Right-click on <i>Default Mod</i> and choose <i>Jump to Source</i>
    - Edit top-of-file items
    - Click <i>Reload all maven Projects</i>

# Step 2

Refactor:
- Project Folder (<i>theDefault</i>)
  - Rename Directory: <i>stsMod</i> (can stay same for all mods)
  - Rename Module: <i>theCoderMod</i>
- Package (<i>theDefault</i>) -> <i>theCoder</i>
- Main Class File (<i>DefaultMod.java</i>) -> <i>TheCoderMod.java</i>
  - (Manually) replace <i>theDefault</i> with <i>theCoder</i>
  - Search for <i>ModID</i> and change reference to <i>TheCoder</i> (or whatever you put in the <i>pom.xml</i> file)
- Character File (<i>TheDefault.java</i>) -> <i>TheCoder.java</i>
- Resources (<i>theDefaultResources</i>) -> <i>theCoderResources</i>
  - Change name of each file under <i>resources -> theCoderResources -> localization.eng</i> to start with <i>TheCoder</i>
  - Shift-Ctrl-r to replace in all files in <i>theCoderResources</i> (and make sure <i>Match Case</i> is <b>on</b>)
  - Replace <i>theDefault</i> with <i>theCoder</i>
  - Replace <i>thedefault</i> with <i>thecoder</i>
  - Character animation file <i>resources -> theCoderResources -> images -> char.defaultCharacter -> Spriter -> theDefaultAnimation.scml</i> to <i>theCoderAnimation.scml</i>
- Do a final check within all <i>.java</i> files to make sure instances of <i>theDefault</i> and <i>thedefault</i> have been updated

# Step 3

- Run Maven Lifecycle <i>package</i>
- Test within Slay the Spire

---
