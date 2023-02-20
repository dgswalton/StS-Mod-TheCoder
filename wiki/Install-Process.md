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
- Main Class File (<i>DefaultMod</i>) -> <i>TheCoderMod</i>
  - (Manually) replace <i>theDefault</i> with <i>theCoder</i>
  - Search for <i>ModID</i> and change reference to <i>TheCoderMod</i> (or whatever you put in the <i>pom.xml</i> file)
- Resources (<i>theDefaultResources</i>) -> <i>theCoderResources</i>
  - Shift-Ctrl-r to replace in all files (and make sure <i>Match Case</i> is <b>on</b>)
  - Replace <i>theDefault</i> with <i>theCoder</i>
  - Replace <i>thedefault</i> with <i>thecoder</i>

# Step 3

- Run Maven Lifecycle <i>package</i>
- Test within Slay the Spire

---
