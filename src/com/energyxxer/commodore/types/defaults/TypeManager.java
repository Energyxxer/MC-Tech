package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeDictionary;

import java.util.HashMap;

/**
 * Contains, retrieves and controls all the types under a namespace.
 *
 * @see Type
 * @see TypeDictionary
 * @see Namespace
 * @see GenericType
 *
 * */
public class TypeManager {
    /**
     * The namespace this type manager belongs to.
     * */
    private final Namespace owner;

    /**
     * A map of all the dictionaries in this type manager, where the key is the case-sensitive category for the types
     * in the dictionary, and the value is said dictionary.
     * */
    private final HashMap<String, TypeDictionary<? extends Type>> dictionaries = new HashMap<>();

    /**
     * A pre-defined type dictionary for all block types.
     * */
    public final TypeDictionary<BlockType> block;
    /**
     * A pre-defined type dictionary for all fluid types.
     * */
    public final TypeDictionary<FluidType> fluid;
    /**
     * A pre-defined type dictionary for all item types.
     * */
    public final TypeDictionary<ItemType> item;
    /**
     * A pre-defined type dictionary for all effect types.
     * */
    public final TypeDictionary<EffectType> effect;
    /**
     * A pre-defined type dictionary for all entity types.
     * */
    public final TypeDictionary<EntityType> entity;
    /**
     * A pre-defined type dictionary for all particle types.
     * */
    public final TypeDictionary<ParticleType> particle;
    /**
     * A pre-defined type dictionary for all enchantment types.
     * */
    public final TypeDictionary<EnchantmentType> enchantment;
    /**
     * A pre-defined type dictionary for all dimension types.
     * */
    public final TypeDictionary<DimensionType> dimension;
    /**
     * A pre-defined type dictionary for all biome types.
     * */
    public final TypeDictionary<BiomeType> biome;


    /**
     * A pre-defined type dictionary for all difficulty types.
     * */
    public final TypeDictionary<DifficultyType> difficulty;
    /**
     * A pre-defined type dictionary for all gamemode types.
     * */
    public final TypeDictionary<GamemodeType> gamemode;
    /**
     * A pre-defined type dictionary for all gamerule types.
     * */
    public final TypeDictionary<GameruleType> gamerule;
    /**
     * A pre-defined type dictionary for all structure types.
     * */
    public final TypeDictionary<StructureType> structure;

    /**
     * A pre-defined type dictionary for all item slot types.
     * */
    public final TypeDictionary<ItemSlot> slot;

    /**
     * Creates a type manager belonging to the given namespace, filled with the standard type dictionaries.
     *
     * @param owner The parent namespace for this type manager.
     * */
    public TypeManager(Namespace owner) {
        this.owner = owner;

        put(this.block = new TypeDictionary<>(owner, "block", (id) -> new BlockType(this.owner, id)));
        put(this.fluid = new TypeDictionary<>(owner, "fluid", (id) -> new FluidType(this.owner, id)));
        put(this.item = new TypeDictionary<>(owner, "item", (id) -> new ItemType(this.owner, id)));
        put(this.effect = new TypeDictionary<>(owner, "effect", (id) -> new EffectType(this.owner, id)));
        put(this.entity = new TypeDictionary<>(owner, "entity", (id) -> new EntityType(this.owner, id)));
        put(this.particle = new TypeDictionary<>(owner, "particle", (id) -> new ParticleType(this.owner, id)));
        put(this.enchantment = new TypeDictionary<>(owner, "enchantment", (id) -> new EnchantmentType(this.owner, id)));
        put(this.dimension = new TypeDictionary<>(owner, "dimension", (id) -> new DimensionType(this.owner, id)));
        put(this.biome = new TypeDictionary<>(owner, "biome", (id) -> new BiomeType(this.owner, id)));

        put(this.difficulty = new TypeDictionary<>(owner, "difficulty", DifficultyType::new));
        put(this.gamemode = new TypeDictionary<>(owner, "gamemode", GamemodeType::new));
        put(this.gamerule = new TypeDictionary<>(owner, "gamerule", GameruleType::new));
        put(this.structure = new TypeDictionary<>(owner, "structure", StructureType::new));

        put(this.slot = new TypeDictionary<>(owner, "slot", ItemSlot::new));
    }

    /**
     * Adds the given dictionary to this type manager's list of dictionaries.
     *
     * @param dict The dictionary to add to this type manager.
     * */
    private void put(TypeDictionary<?> dict) {
        dictionaries.put(dict.getCategory(), dict);
    }

    /**
     * Adds all the type definitions from the passed object into this type manager's dictionaries.
     *
     * @param other The type manager object whose types should be added to this type manager.
     * */
    public void join(TypeManager other) {
        for(TypeDictionary<? extends Type> fromThat : other.dictionaries.values()) {
            boolean useNamespace = !fromThat.list().isEmpty() && fromThat.list().toArray(new Type[0])[0].useNamespace();
            TypeDictionary<?> dict = createDictionary(fromThat.getCategory(), useNamespace);
            for(Type t : fromThat.list()) {
                Type newType = dict.create(t.getName());
                newType.putProperties(t.getProperties());
            }
        }
    }

    /**
     * Creates a dictionary for the specified category, and adds it to this type manager.
     * The type this new dictionary will create is of the class {@link GenericType}.<br>
     * If the category specified already exists in this type manager, that will be returned instead.
     *
     * @param category The case-sensitve category string that represents types created by the new dictionary.
     * @param useNamespace Whether the types created by the new dictionary should be printed onto
     *                     commands with the <code>namespace:</code> prefix. <code>true</code> if it should,
     *                     <code>false</code> if it shouldn't.
     *                     If the specified category already exists in this type manager, this parameter will have
     *                     no effect.
     *
     * @return The dictionary for the specified category. If the category exists before the method call,
     * that will be returned. Otherwise, a new dictionary is created and returned.
     * */
    public TypeDictionary<?> createDictionary(String category, boolean useNamespace) {
        if(dictionaries.containsKey(category)) return dictionaries.get(category);
        TypeDictionary<?> newDict = new TypeDictionary<>(owner, category, (id) -> new GenericType(category, (useNamespace) ? this.owner : null, id));
        put(newDict);
        return newDict;
    }

    /**
     * Retrieves a dictionary for a specified category.
     *
     * @param category The category whose dictionary is to be returned.
     *
     * @return The type dictionary for the specified category, if it exists. <code>null</code> if it doesn't exist.
     * */
    public TypeDictionary<?> getDictionary(String category) {
        return dictionaries.get(category);
    }
}
