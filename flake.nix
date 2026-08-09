{
  description = "Java 25 + Springboot 4 dev environment";

  inputs = {
    nixpkgs.url = "github:NixOs/nixpkgs/26.05";
  };

  outputs = { nixpkgs, ...}:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        packages = [
          pkgs.jdk25
          pkgs.maven
        ];

        shellHook = ''
          export JAVA_HOME="${pkgs.jdk25}"

          echo "Java: $(java -version 2>&1 | head -n 1)"
          echo "Maven: $(mvn -version | head -n 1)"
        '';
      };
    };
}
