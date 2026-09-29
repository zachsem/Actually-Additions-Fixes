# Validation

## Target environment

- Minecraft 1.12.2
- Actually Additions 1.12.2-r152
- MixinBooter 11.17
- Forge 14.23.5.2859 (recommended baseline tested)
- Forge 14.23.5.2864 (latest baseline tested)
- Universal Tweaks 1.21.0 compatibility tested

## Phantom Breaker — unpatched baseline

The upstream issue was reproduced on Actually Additions r152.

Test order:

1. Break stone by hand.
2. Break stone with the regular Auto-Breaker.
3. Break stone with the Phantom Breaker.

Observed with the temporary AA Event Probe:

- Manual break: \`BreakEvent\` + \`HarvestDropsEvent\` fired.
- Regular Auto-Breaker: \`BreakEvent\` + \`HarvestDropsEvent\` fired.
- Phantom Breaker: neither event fired, while the block itself was broken successfully.

Result: issue #1322 confirmed on r152.

## Phantom Breaker — patched validation

Confirmed in single-player:

- Manual break still fires the expected Forge events.
- Regular Auto-Breaker still fires the expected Forge events.
- Phantom Breaker now fires \`BreakEvent\` and \`HarvestDropsEvent\` once for an actual block break.
- Leaving the Phantom Breaker linked to air does not generate repeated synthetic break/harvest events.
- The Phantom Breaker continues to break its target normally.

Confirmed with Universal Tweaks 1.21.0 installed:

- Game starts without a Mixin conflict.
- The same three-break event test passes.
- No repeated events are generated for an empty linked position.

Confirmed on Forge 14.23.5.2859 and 14.23.5.2864.

Confirmed on a dedicated Forge 14.23.5.2864 server with Universal Tweaks 1.21.0 installed.

Not separately exercised as dedicated test cases:

- A third-party \`BreakEvent\` listener explicitly cancelling the event.
- A third-party \`HarvestDropsEvent\` listener rewriting the drop list.

The patch uses Actually Additions' own \`WorldUtil.fireFakeHarvestEventsForDropChance\` helper, the same event helper used by its regular Auto-Breaker.

## BioMash — unpatched baseline

A temporary test helper changed Actually Additions' \`item_misc\` maximum stack size from 64 to 16.

Without Actually Additions Fixes installed, the unpatched BioMash recipe continued to produce outputs larger than the item's real 16-item stack limit, confirming that r152 uses its hard-coded 64 ceiling.

Result: issue #1336 confirmed on r152.

## BioMash — patched validation

With the same temporary stack-limit helper and Actually Additions Fixes installed:

- Two steaks produced 14 Mashed Food, which is below the temporary limit of 16.
- Three steaks produced no crafting result because the calculated output would be 21, above the temporary limit of 16.

This A/B behavior was confirmed in single-player.

The same below-limit/above-limit behavior was also confirmed while connected to a dedicated Forge 14.23.5.2864 server, with the temporary helper active on both sides.

The temporary BioMash helper and AA Event Probe are test utilities only and are not part of the release JAR.

## Excluded issue

The sealed Storage Crate crafting report is not included. The reported Divine Journey 2 setup replaces Actually Additions' native upgrade recipes, so the report does not currently establish a native r152 Actually Additions bug.
