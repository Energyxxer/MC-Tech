package com.energyxxer.commodore;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.commands.CloneCommand;
import com.energyxxer.commodore.commands.CloneFilteredCommand;
import com.energyxxer.commodore.commands.CloneMaskedCommand;
import com.energyxxer.commodore.commands.FillCommand;
import com.energyxxer.commodore.commands.FillDestroyCommand;
import com.energyxxer.commodore.commands.FillHollowCommand;
import com.energyxxer.commodore.commands.FillKeepCommand;
import com.energyxxer.commodore.commands.FillOutlineCommand;
import com.energyxxer.commodore.commands.FillReplaceCommand;
import com.energyxxer.commodore.commands.GamemodeCommand;
import com.energyxxer.commodore.commands.TellrawCommand;
import com.energyxxer.commodore.commands.scoreboard.ScoreGet;
import com.energyxxer.commodore.commands.scoreboard.ScorePlayersOperation;
import com.energyxxer.commodore.commands.scoreboard.ScoreSet;
import com.energyxxer.commodore.commands.scoreboard.ScoreboardManipulation;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionComment;
import com.energyxxer.commodore.functions.FunctionHeaderComment;
import com.energyxxer.commodore.project.CommandModule;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.ObjectiveManager;
import com.energyxxer.commodore.selector.Selector;
import com.energyxxer.commodore.selector.TagArgument;
import com.energyxxer.commodore.selector.TypeArgument;
import com.energyxxer.commodore.textcomponents.ScoreTextComponent;
import com.energyxxer.commodore.types.BlockType;
import com.energyxxer.commodore.types.Gamemode;

public final class CommandTest {
    public static void main(String[] args) {
        /*
        Function function = new Function("test:function");

        ObjectiveManager objMgr = new ObjectiveManager();

        Entity entity0 = new GenericEntity(new Selector(Selector.BaseSelector.ALL_ENTITIES));
        entity0.getSelector().addArguments(new TypeArgument("bat"), new TagArgument("a"), new TagArgument("!b"));
        Entity entity1 = new GenericEntity(new Selector(Selector.BaseSelector.RANDOM_PLAYER));
        entity1.getSelector().addArguments(new NBTArgument(new TagCompound(new TagByte("OnGround",1))));

        //System.out.println("entity0 = " + entity0.getSelector());
        //System.out.println("entity1 = " + entity1.getSelector());

        //System.out.println();

        SayCommand sayHi = new SayCommand("hi");
        Block block = new Block(new BlockType("furnace"), Blockstate.parseRaw("facing=west"), new TagCompound(new TagShort("BurnTime", (short) 10)));
        SetblockCommand setblock = new SetblockCommand(new CoordinateSet(0, 3, 0, Coordinate.Type.RELATIVE), block);
        ExecuteCommand exec = new ExecuteCommand(setblock);
        exec.addModifier(new ExecuteAsEntity(entity0));
        exec.addModifier(new ExecuteAtEntity(entity0));
        exec.addModifier(new ExecuteAlignment(true, false, true));
        exec.addModifier(new CoordinateSet(0.5, 0, 0.5, Coordinate.Type.RELATIVE));
        function.append(exec);
        //System.out.println("exec = " + exec.getRawCommand());

        //System.out.println();

        TextStyle style0 = new TextStyle(TextColor.BLUE, TextStyle.BOLD + TextStyle.STRIKETHROUGH + TextStyle.OBFUSCATED);
        TextStyle style1 = new TextStyle(TextStyle.ITALIC + TextStyle.STRIKETHROUGH + TextStyle.UNDERLINE);

        //System.out.println("style0 = " + style0);
        //System.out.println("style1 = " + style1);

        //System.out.println("style1 (masked to style0) = " + style1.toString(style0));

        ListTextComponent ltc = new ListTextComponent();

        StringTextComponent tc = new StringTextComponent("Hello World!");
        tc.addEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new StringTextComponent("why", style1)));
        tc.setStyle(style0);
        ltc.append(tc);

        StringTextComponent tc2 = new StringTextComponent(" Salutations Universe!");
        tc2.setStyle(style0);
        ltc.append(tc2);
        function.append(new TellrawCommand(new GenericEntity(new Selector(Selector.BaseSelector.ALL_PLAYERS)), ltc));
        //System.out.println("ltc = " + ltc);

        Block cb = new Block(
                new BlockType("command_block"),
                Blockstate.parseRaw("facing=down,powered=true"),
                new TagCompound(new TagString("Command","say hi"), new TagInt("SuccessCount",2))
                );
        //System.out.println("cb = " + cb);

        TagCommand tagcmd = new TagCommand(TagCommand.Action.ADD, entity1, "on_ground");
        function.append(tagcmd);
        //System.out.println("tagcmd = " + tagcmd.getRawCommand());

        DataCommand datacmd = new DataCommand(entity0, "BatFlags", 1);
        function.append(datacmd);
        //System.out.println("datacmd = " + datacmd.getRawCommand());*/

        CommandModule module = new CommandModule("Commodore Test","ct");
        ObjectiveManager objMgr = module.getObjectiveManager();

        Function function = module.getFunctionManager().get("test:scores");

        Entity entity = new GenericEntity(new Selector(Selector.BaseSelector.ALL_ENTITIES));
        entity.getSelector().addArguments(new TypeArgument("bat"), new TagArgument("a"), new TagArgument("!b"));

        function.append(new FunctionHeaderComment("SCOREBOARD ACCESS OPTIMIZATION"));

        LocalScore a = new LocalScore(objMgr.get("A"), entity.getScoreManager());
        LocalScore b = new LocalScore(objMgr.get("B"), entity.getScoreManager());

        ScoreboardManipulation op0 = new ScoreSet(a, 5);
        ScoreboardManipulation op1 = new ScorePlayersOperation(b, ScorePlayersOperation.Operation.ASSIGN, a);
        //ScoreboardManipulation op2 = new ScoreSet(b, 3);
        ScoreboardManipulation op3 = new ScoreGet(b);

        function.append(op0);
        function.append(op1);
        //function.append(op2);
        function.append(op3);

        function.append(new TellrawCommand(new GenericEntity(new Selector(Selector.BaseSelector.ALL_PLAYERS)), new ScoreTextComponent(b)));

        function.append(new FunctionComment("CLONE COMMANDS"));

        function.append(new CloneCommand(new CoordinateSet(0,0,0, Coordinate.Type.RELATIVE),new CoordinateSet(2,2,2, Coordinate.Type.RELATIVE),new CoordinateSet(5,5,5)));
        function.append(new CloneCommand(new CoordinateSet(0.5,0.5,0.5),new CoordinateSet(2.5,2.5,2.5, Coordinate.Type.RELATIVE),new CoordinateSet(5,5,5), CloneCommand.SourceMode.FORCE));
        function.append(new CloneMaskedCommand(new CoordinateSet(0,0,0),new CoordinateSet(2,2,2),new CoordinateSet(5,5,5)));
        function.append(new CloneMaskedCommand(new CoordinateSet(0,0,0),new CoordinateSet(2,2,2),new CoordinateSet(5,5,5), CloneCommand.SourceMode.FORCE));
        function.append(new CloneFilteredCommand(new CoordinateSet(0,0,0),new CoordinateSet(2,2,2),new CoordinateSet(5,5,5), new Block(new BlockType("#minecraft:buttons"))));
        function.append(new CloneFilteredCommand(new CoordinateSet(0,0,0),new CoordinateSet(2,2,2),new CoordinateSet(5,5,5), new Block(new BlockType("#minecraft:buttons")), CloneCommand.SourceMode.FORCE));

        function.append(new FunctionComment("FILL COMMANDS"));

        CoordinateSet pos1 = new CoordinateSet(23, 40, -934);
        CoordinateSet pos2 = new CoordinateSet(49, 49, -920);

        function.append(new FillCommand(pos1, pos2, new Block(new BlockType("minecraft:command_block"))));
        function.append(new FillDestroyCommand(pos1, pos2, new Block(new BlockType("minecraft:air"))));
        function.append(new FillOutlineCommand(pos1, pos2, new Block(new BlockType("minecraft:white_concrete"))));
        function.append(new FillHollowCommand(pos1, pos2, new Block(new BlockType("minecraft:blue_concrete"))));
        function.append(new FillKeepCommand(pos1, pos2, new Block(new BlockType("minecraft:fire"))));
        function.append(new FillReplaceCommand(pos1, pos2, new Block(new BlockType("minecraft:spruce_planks")), new Block(new BlockType("minecraft:oak_planks"))));

        function.append(new FunctionComment("OTHERS"));

        function.append(new GamemodeCommand(new Gamemode("spectator"), new GenericEntity(new Selector(Selector.BaseSelector.ALL_PLAYERS))));

        module.compile();

        System.out.println(function);

        System.out.println(function.getResolvedContent());

        System.out.println(a.getAccessLog());
        System.out.println(b.getAccessLog());
    }
}