const Discord = require("discord.js");

const client = new Discord.Client();
const player = require("./Player.js");
const token = require("../resources/token.json");

var activePlayers = [];
var prefix = "!";

client.login(token.token);

client.on("ready", () => {
	console.log("Bot up and running.");
});

client.on("message", (message) => {
	for (var i = 0; i < activePlayers.length; i++) {
		if (activePlayers[i].id == message.author.id) {
			processMessage(message, activePlayers[i]);
			return;
		}
	}
	
	activePlayers.push(player.initialisePlayer(message.author));
	message.channel.send("Welcome to MazeOfMaws!");
});

function processMessage(message, user) {
	if (message.author.bot || !message.content.startsWith(prefix)) {
		return;
	}
	
	if (message.content.startsWith(prefix + "start")) {
		message.channel.send("Starting game!");
		player.startGame(user);
	} else if (message.content.startsWith(prefix + "abort")) {
		message.channel.send("Ending game!");
		player.endGame(user);
	} else if (message.content.startsWith(prefix + "status")) {
		printStatus(message, user);
	}
}

function printStatus(message, user) {
	switch(user.gameState) {
		case 0:
			message.channel.send("You do not currently have an active game.");
			break;
		case 1:
			message.channel.send("You're in game!");
			break;
		default:
			message.channel.send("You appear to be in an illegal gamestate.");
	}
}