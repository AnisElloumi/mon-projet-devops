// test.js
const additionner = require('./index.js');

// On vérifie si 2 + 3 font bien 5
if (additionner(2, 3) === 5) {
  console.log("✅ Le test est réussi !");
  process.exit(0); // 0 signifie "Tout va bien" au pipeline
} else {
  console.error("❌ Le test a échoué !");
  process.exit(1); // 1 signifie "Erreur" au pipeline
}